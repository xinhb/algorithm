package com.imooc.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.passbook.constant.Constants;
import com.imooc.passbook.constant.ErrorCode;
import com.imooc.passbook.dao.MerchantsDao;
import com.imooc.passbook.entity.Merchants;
import com.imooc.passbook.service.IMerchantsServ;
import com.imooc.passbook.vo.CreateMerchantsRequest;
import com.imooc.passbook.vo.CreateMerchantsResponse;
import com.imooc.passbook.vo.PassTemplate;
import com.imooc.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h1>商户服务接口实现</h1>
 * Created by Qinyi.
 */
@Slf4j
@Service
public class MerchantsServImpl implements IMerchantsServ {

    /** Merchants 数据库接口 */
    private final MerchantsDao merchantsDao;

    /** kafka 客户端 */
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MerchantsServImpl(MerchantsDao merchantsDao,
                             KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional

    /**
     * <h2>商家注册</h2>
     * @param request {@link CreateMerchantsRequest}  传入创建商户的请求对象
     * @return {@link Response} 返回通用响应对象
     * */
    public Response createMerchants(CreateMerchantsRequest request) {
        //利用无参构造创建通用响应对象, response ,初始值(0,"",null)
        Response response = new Response();
        //创建商户后的响应对象 merchantsResponse
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();

        //验证商户是否注册过,返回错误码
        ErrorCode errorCode = request.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS) {
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            //网页上的请求对象转为商户对象，再merchantsDao接口保存该对象，再获取数据库中这个对象的id,作为创建商户请求的返回值。
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        //填充通用返回，保证一致性
        response.setData(merchantsResponse);

        return response;
    }

    /**
     * <h2>通过id查找商家信息</h2>
     * @param  id {@link Integer}  商家的唯一id
     * @return {@link Response}   通用响应对象（包含Merchats对象）
     * */
    @Override
    public Response buildMerchantsInfoById(Integer id) {

        Response response = new Response();

        Merchants merchants = merchantsDao.findById(id);

        //没找到返回错误码，错误描述、无merchans对象
        if (null == merchants) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }

        response.setData(merchants);

        return response;
    }

    /**
     * <h2>投放优惠券到kafka中</h2>
     * @param template {@link PassTemplate}
     * @return {@link Response}
     *
     * */
    @Override
    public Response dropPassTemplate(PassTemplate template) {

        Response response = new Response();
        //验证将要投出去的优惠券某个商家的
        ErrorCode errorCode = template.validate(merchantsDao);

        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            //template对象转为json格式
            String passTemplate = JSON.toJSONString(template);
            //send方法三个参数的含义；topic key data
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );
            //打印日志
            log.info("DropPassTemplates: {}", passTemplate);
        }
        return response;
    }
}

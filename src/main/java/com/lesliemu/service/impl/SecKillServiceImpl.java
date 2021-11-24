package com.lesliemu.service.impl;

import com.lesliemu.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Slf4j
@Service
public class SecKillServiceImpl implements SecKillService {


    @Override
    public Boolean doSecKill(String prodid, String userid) {
        String qtKey = "sk:" + prodid + ":qt";
        String usrKey = "sk:" + prodid + ":usr";
        Jedis jedis = new Jedis("101.201.36.29",6379);
        String qtStr = jedis.get(qtKey);
        Long qt = null;
        try {
            if (qtStr == null) {
                log.debug("商品不存在");
                return null;
            }
            qt = Long.valueOf(qtStr);
            if (jedis.sismember(usrKey, userid)) {
                log.debug("用户已参加过秒杀");
                return false;
            } else if (qt < 1) {
                log.debug("没货啦");
                return false;
            } else {
                log.debug("还剩" + jedis.decr(qtKey) + "件");
                jedis.sadd(usrKey, userid);
                log.debug(userid + "已成功购买");
                return true;
            }
        } finally {
            jedis.close();
        }
    }

}

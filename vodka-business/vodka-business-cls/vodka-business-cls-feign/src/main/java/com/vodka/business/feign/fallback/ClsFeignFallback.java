package com.vodka.business.feign.fallback;

import com.vodka.business.feign.cls.ClsFeign;
import com.vodka.common.base.result.Codes;
import com.vodka.common.base.result.R;
import com.vodka.common.base.result.RUtil;
import com.vodka.data.entity.Cls;
import org.springframework.stereotype.Component;

/**
 * @author Breeze
 * @date 2023/9/17 15:20
 * @description
 */
@Component
public class ClsFeignFallback implements ClsFeign {
    @Override
    public R<Cls> getClsByCId(Long cId) {
        return RUtil.create(Codes.DEGRADE_ERROR);
    }
}

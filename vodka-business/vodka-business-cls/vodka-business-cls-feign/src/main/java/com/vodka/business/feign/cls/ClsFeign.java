package com.vodka.business.feign.cls;

import com.vodka.business.feign.fallback.ClsFeignFallback;
import com.vodka.common.base.result.R;
import com.vodka.data.entity.Cls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Breeze
 * @date 2023/9/16 14:31
 * @description ClsFeign
 */
@FeignClient(name = "vodka-cls", fallback = ClsFeignFallback.class)
public interface ClsFeign {
    @GetMapping("/cls/getByCId")
    R<Cls> getClsByCId(@RequestParam("cId") Long cId);
}

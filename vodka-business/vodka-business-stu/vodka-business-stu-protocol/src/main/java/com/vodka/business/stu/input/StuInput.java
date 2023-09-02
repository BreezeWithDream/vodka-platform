package com.vodka.business.stu.input;

import com.vodka.business.stu.validator.StuAgeValidator;
import com.vodka.common.web.validate.annotation.VodkaValidator;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Breeze
 * @date 2023/4/26 22:19
 * @description
 */
@Data
public class StuInput implements Serializable {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Min(value = 10, message = "年龄不小于10")
    @Max(value = 30, message = "年龄不大于30")
    @NotNull(message = "年龄不能为空")
    @VodkaValidator(message = "年龄校验失败", validator = StuAgeValidator.class)
    private Integer age;

    @Email(message = "邮箱格式不正确")
    private String email;

    // @Past(message = "生日范围不正确")
    private Date birthday;

    private String sex;
}

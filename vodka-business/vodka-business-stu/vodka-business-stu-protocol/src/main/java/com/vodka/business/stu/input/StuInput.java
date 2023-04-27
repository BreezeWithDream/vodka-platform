package com.vodka.business.stu.input;

import com.vodka.business.stu.annotation.VodkaValid;
import lombok.Data;

import javax.validation.constraints.*;
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
    private Integer age;

    @Email(message = "邮箱格式不正确")
    private String email;

    // @Past(message = "生日范围不正确")
    private Date birthday;

    @VodkaValid
    private String sex;
}

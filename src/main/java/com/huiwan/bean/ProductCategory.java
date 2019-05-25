package com.huiwan.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mjl on 2019/2/27.
 */
@Entity
@Table(name = "product_category")
@Data
@DynamicUpdate
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;//类目编号

    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;//类目名称

    private Integer categoryType;//类目编号

    private Date createTime;

    private Date updateTime;

}

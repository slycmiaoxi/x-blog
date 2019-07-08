package cn.slycmiaoxi.dto;

import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 算法建模dto
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-29
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlgotithmsInfoDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 算法id
     */
    private String algotithmsInfoId;
    
    /**
     * 算法类型
     */
    private String algotithmsTypeId;
    
    /**
     * 算法类型名称
     */
    private String algotithmsTypeName;
    
    /**
     * 算法标题
     */
    private String algotithmsTitle;
    
    /**
     * 算法描述
     */
    private String algotithmsDescription;
    
    /**
     * 算法输入描述
     */
    private String inputExpression;
    
    /**
     * 算法输出描述
     */
    private String outputExpression;
    
    /**
     * 算法输入样例
     */
    private String sampleInput;
    
    /**
     * 算法输出样例
     */
    private String sampleOut;
    
    /**
     * 算法上传作者
     */
    private String author;
    
    /**
     * 算法函数名称
     */
    private String funcName;
    
    /**
     * 算法索引
     */
    private Long funcIndex;
    
    /**
     * 算法核心代码
     */
    private String algotithmsCode;
    
    /**
     * 算法交互代码
     */
    private String controllerCode;
    
}

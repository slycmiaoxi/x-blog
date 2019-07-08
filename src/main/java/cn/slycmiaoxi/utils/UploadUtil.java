package cn.slycmiaoxi.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 本地上传工具类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
@Slf4j
public class UploadUtil {
    
    /**
     * 获得上传路径
     * 
     * @param multipartFile MultipartFile对象
     * @param request 域对象
     * @return 上传路径
     * @throws Exception 异常
     * @author slycmiaoxi
     * @since 2019-06-17
     */
    public static String uploadFile(MultipartFile multipartFile, HttpServletRequest request)
        throws Exception {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/");
        // 构建图片保存的目录
        String logoPathDir = "/userImgs" + dateformat.format(new Date());
        
        // 得到图片保存目录的真实路径
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);
        File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists()) {
            logoSaveFile.mkdirs();
        }
        
        // 获取文件的后缀
        String suffix =
            multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        // 使用UUID生成文件名称
        String logImageName = UUID.randomUUID().toString() + suffix;
        // 构建文件名称
        // String logImageName = multipartFile.getOriginalFilename();
        // 拼成完整的文件保存路径加文件
        String fileName = logoRealPathDir + File.separator + logImageName;
        
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        }
        catch (IllegalStateException e) {
            log.error("error", e);
        }
        catch (IOException e) {
            log.error("error", e);
        }
        
        return logoPathDir + logImageName;
    }
    
}

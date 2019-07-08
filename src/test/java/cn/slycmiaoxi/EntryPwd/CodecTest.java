package cn.slycmiaoxi.EntryPwd;

import cn.slycmiaoxi.utils.CodecUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Auther: 朱维亮
 * @Date: 2019/6/17 23:02
 * @Description:
 */
@Slf4j
public class CodecTest {


    public void enAndDeCodeTest() throws Exception {
        String str = "1";
        String decodeStr = CodecUtils.encodeData(str);
        log.info(decodeStr);
        String enStr = CodecUtils.decodeData(decodeStr);
        log.info(enStr);

    }
}

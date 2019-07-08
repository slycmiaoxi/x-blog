package cn.slycmiaoxi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.slycmiaoxi.common.Const;
import cn.slycmiaoxi.entity.TUserRole;
import cn.slycmiaoxi.enumerate.AuthorEnum;
import cn.slycmiaoxi.service.ITUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.slycmiaoxi.common.Constants;
import cn.slycmiaoxi.dao.TUserMapper;
import cn.slycmiaoxi.dto.UserDto;
import cn.slycmiaoxi.entity.TUser;
import cn.slycmiaoxi.enumerate.Enable;
import cn.slycmiaoxi.pagehelper.Page;
import cn.slycmiaoxi.pagehelper.PageModal;
import cn.slycmiaoxi.pagehelper.PageUtil;
import cn.slycmiaoxi.service.ITUserService;
import cn.slycmiaoxi.utils.CodecUtils;

/**
 * <p>
 * 用户服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-25
 */
@Service
public class ITUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {
    
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private ITUserRoleService itUserRoleService;
    
    @Override
    public TUser getUserByName(String nickName) {
        if (StringUtils.isEmpty(nickName)) {
            return null;
        }
        
        List<TUser> list = tUserMapper
            .selectList(new EntityWrapper<TUser>().eq("nick_name", nickName).eq("delete_flag", Enable.YES.getValue()));
        
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public TUser getUserByNameAndPwd(String nickName, String pwd) {
        if (StringUtils.isEmpty(nickName) || StringUtils.isEmpty(pwd)) {
            return null;
        }
        
        pwd = new Md5Hash(pwd, Constants.SALT).toString();
        EntityWrapper<TUser> wrapper = new EntityWrapper<>();
        wrapper.eq("nick_name", nickName).eq("user_pwd", pwd).eq("delete_flag", Enable.YES.getValue());
        List<TUser> list = tUserMapper.selectList(wrapper);
        
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public boolean add(UserDto dto) {
        // 1.校验参数
        boolean checkParams = convert(dto);
        if (!checkParams) {
            return false;
        }
        // 2.dto -> po
        TUser user = getPoByDto(dto);
        // 3.入数据库
        tUserMapper.insert(user);
        TUserRole userRole = new TUserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(AuthorEnum.USER.getCode());
        itUserRoleService.insert(userRole);
        return true;
    }
    
    @Override
    public void updateInfoById(TUser user) {
        if (null == user || null == user.getUserId()) {
            return;
        }
        tUserMapper.updateById(user);
    }
    
    @Override
    public long queryTotal() {
        return tUserMapper.queryTotal(null);
    }
    
    @Override
    public PageModal queryListbyPage(Page page, Map<String, Object> map) {
        page = PageUtil.createPage(page, tUserMapper.queryTotal(map).intValue());
        map.put("beginIndex", page.getBeginIndex());
        map.put("everyPage", page.getEveryPage());
        List<TUser> pageList = tUserMapper.queryList(map);
        List<UserDto> dtos = new ArrayList<>();
        // 加密
        pageList.stream().forEach(x -> {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(x, dto, new String[] {"userId"});
            dto.setUserId(CodecUtils.encodeData(String.valueOf(x.getUserId())));
            dtos.add(dto);
        });
        PageModal pg = new PageModal();
        pg.setPage(page);
        pg.setList(dtos);
        return pg;
    }
    
    /**
     * dto -> po
     *
     * @param dto 用户信息dto
     * @return po实体类
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    private TUser getPoByDto(UserDto dto) {
        TUser user = new TUser();
        BeanUtils.copyProperties(dto, user, new String[] {"userId"});
        user.setUserHeadimg(Constants.DEFAULT_HEAD_IMG);
        user.setGmtCreate(new Date());
        user.setDeleteFlag(Enable.YES.getValue());
        user.setUserState(Enable.YES.getValue());
        return user;
    }
    
    /**
     * 校验参数
     *
     * @param dto 用户信息dto
     * @return 校验结果
     * @author slycmiaoxi
     * @since 2019-06-25
     */
    private boolean convert(UserDto dto) {
        // 1.dto不能为空与防止报文
        if (null == dto || StringUtils.isNotEmpty(dto.getUserId())) {
            return false;
        }
        // 2.用户名与密码不能为空
        if (StringUtils.isEmpty(dto.getNickName()) || StringUtils.isEmpty(dto.getUserPwd())) {
            return false;
        }
        // 3.邮箱不能为空
        if (StringUtils.isEmpty(dto.geteMail())) {
            return false;
        }
        // 4.密码加密
        dto.setUserPwd(new Md5Hash(dto.getUserPwd(), Constants.SALT).toString());
        return true;
    }
}

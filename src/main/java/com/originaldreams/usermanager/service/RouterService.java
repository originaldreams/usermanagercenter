package com.originaldreams.usermanager.service;

import com.originaldreams.common.entity.Router;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.common.router.MyRouter;
import com.originaldreams.common.router.MyRouterObject;
import com.originaldreams.usermanager.cache.MyCache;
import com.originaldreams.usermanager.mapper.RoleRoutersMapper;
import com.originaldreams.usermanager.mapper.RouterMapper;
import com.originaldreams.usermanagercenter.entity.RoleRouters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouterService {
    @Autowired
    private RouterMapper routerMapper;

    @Autowired
    private RoleRoutersMapper roleRoutersMapper;

    private Logger logger = LoggerFactory.getLogger(RouterService.class);

    /**
     * 初始化路由
     *
     */
    public void initRouters(){
        List<MyRouterObject> list = new ArrayList<>(MyRouter.routerMapGet.values());
        routerMapper.deleteAll();
        insert(list,"GET");
        list = new ArrayList<>(MyRouter.routerMapPost.values());
        insert(list,"POST");
        list = new ArrayList<>(MyRouter.routerMapDelete.values());
        insert(list,"DELETE");
        list = new ArrayList<>(MyRouter.routerMapPut.values());
        insert(list,"PUT");
        logger.trace("initRouters OK");
    }
    private void insert(List<MyRouterObject> list,String method){
        for(MyRouterObject routerObject :list){
            Router router = Router.parseRouter(routerObject);
            router.setRequestMethod(method);
            MyCache.routerMap.put(router.getId(),router);
            routerMapper.insert(router);
        }
    }

    public MyServiceResponse getAll(){
        return new MyServiceResponse(routerMapper.getAll());
    }
    public MyServiceResponse getRoutersByRoleId(int roleId){
        return new MyServiceResponse(routerMapper.getRoutersByRoleId(roleId));
    }

    public MyServiceResponse addRouterForRole(RoleRouters roleRouters){
        return new MyServiceResponse(roleRoutersMapper.insert(roleRouters));
    }

    public MyServiceResponse getRouterIdsByUserId(Integer userId){
        List<RoleRouters> list = roleRoutersMapper.getByUserId(userId);
        List<Integer> result = new ArrayList<>();
        for(RoleRouters roleRouters : list){
            result.add(roleRouters.getRouterId());
        }
        return new MyServiceResponse(result);
    }


    public Router getById(Integer id){

        return routerMapper.getById(id);
    }



    public Integer insert(Router router){
        return routerMapper.insert(router);
    }

    public Integer deleteById(Integer id){
        return routerMapper.deleteById(id);
    }

    public Integer update(Router router){
        return routerMapper.update(router);
    }


}
package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @Author: Admin-han
 * @DateTime: 2020/4/29 0:12
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();

    /**
     * 根据类别进行线路分页查询
     *
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pageBean = new PageBean<>();
        // 设置当前页码
        pageBean.setCurrentPage(currentPage);
        // 设置页码条数
        pageBean.setPageSize(pageSize);

        // 获取总记录数，并设置
        int totalCount = routeDao.findTotalCount(cid, rname);
        pageBean.setTotalCount(totalCount);
        // 获取页面起始条数
        int start = (currentPage - 1) * pageSize;
        // 获取页面数据集合,并设置
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pageBean.setList(list);

        // 获取总页码，并设置
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    /**
     * 根据id查询一个route对象
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        // 1.首先根据rid查询一个route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        // 2.根据route对象中的id查询图片集合信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        route.setRouteImgList(routeImgList);
        // 3.根据route对象中的sid查询商家详细信息
        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);
        return route;
    }
}

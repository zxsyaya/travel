package com.hand.travel.service.impl;

import com.hand.travel.dao.FavoriteDao;
import com.hand.travel.dao.impl.FavoriteDaoImpl;
import com.hand.travel.service.FavoriteService;

/**
 * @Author: Admin-han
 * @DateTime: 2020/5/1 10:03
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * 根据线路rid和用户uid判断是否收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(String rid, int uid) {
        return favoriteDao.isFavorite(Integer.parseInt(rid), uid);
    }

    /**
     * 根据线路rid和用户uid进行添加收藏操作
     *
     * @param rid
     * @param uid
     */
    @Override
    public void addFavorite(String rid, int uid) {
        favoriteDao.addFavorite(rid, uid);
    }
}

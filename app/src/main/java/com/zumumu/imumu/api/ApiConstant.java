package com.zumumu.imumu.api;

/**
 * Created by PC_p on 2016/12/26.
 */

public class ApiConstant {

    public static final String HOME_URL = "http://192.168.2.120:8081";
    public static final String HOME_PAGE_URL = HOME_URL + "/mumu/shop/showHomeImg.do";
    public static final String LOGIN_URL = HOME_URL + "/mumu/user/login.do";
    public static final String SIGN_CODE_URL = HOME_URL + "/mumu/user/registerCode.do";
    public static final String SIGNIN_URL = HOME_URL + "/mumu/user/register.do";
    public static final String FORGET_URL = HOME_URL + "/mumu/user/forgetPwd.do";
    public static final String FORGET_CODE_URL = HOME_URL + "/mumu/user/forgetCode.do";
    public static final String SHOPLIST_URL = HOME_URL + "/mumu/goods/showGoodsList.do";
    public static final String GOODS_DETAILS = HOME_URL + "/mumu/goods/showGoodsDetails.do";
    public static final String ADD_TO_SHOPCART = HOME_URL + "/mumu/goods/addCart.do";
    public static final String SHOW_SHOPCART = HOME_URL +"/mumu/goods/showCart.do";
    public static final String DELETE_SHOPCART = HOME_URL+"/mumu/goods/delete.do";
    public static final String ADD_ADDRES_URL = HOME_URL+ "/mumu/address/addUserAdderss.do";
    public static final String SHOW_ADDRESS = HOME_URL + "/mumu/address/showAddress.do";
    public static final String DELETE_ADDRESS = HOME_URL + "/mumu/address/deleteAdderss.do";
    public static final String DETAIL_BUY_NOW = HOME_URL + "/mumu/order/addOrder.do";
    public static final String ACCOUNTS_URL = HOME_URL + "/mumu/order/submitOrder.do";



    public static final String DELETE_CART = HOME_URL + "/mumu/goods/deleteCart.do";    //购物车单条删除

    public static final String ALIPAY = HOME_URL + "/mumu/order/alipay.do";   //支付宝
    public static final String BALANCE_PAY = HOME_URL +"/mumu/order/balancePayOrder.do";

}

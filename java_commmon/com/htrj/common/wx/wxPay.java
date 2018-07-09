package com.htrj.common.wx;

public class wxPay {
	
	 /**
     * 应用编号（微信公众号编号）
     */
    private String appId = "wx4c05486c196ff0d5";
    /**
     * 商户号码
     */
    private String mchId = "";
    /**
     * 支付码
     */
    private String payKey = "caofeidian1718715371chenjianwei1";
    /**
     * 统一下单URL
     */
    private String unifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	
	
	/**
     * 统一下单
     * @Title: unifiedOrder 
     * @Description: TODO 
     * @param: @param openId 微信用户openId
     * @param: @param orderId 订单ID
     * @param: @param money 订单总价，单位：分
     * @param: @param callbackUrl 回调路径
     * @param: @return
     * @param: @throws Exception
     * @return: String
     */
/*    public String unifiedOrder(String openId,String orderId,int money,String callbackUrl) throws Exception{
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        unifiedOrder.setAppid(appId);
        unifiedOrder.setAttach("hehedesk");

        unifiedOrder.setBody("hehedesk");
        unifiedOrder.setMch_id(mchId);

        String nonce = UUID.randomUUID().toString().substring(0, 30);
        unifiedOrder.setNonce_str(nonce);
        unifiedOrder.setNotify_url(callbackUrl);

        unifiedOrder.setOpenid(openId);
        unifiedOrder.setOut_trade_no(orderId);

        unifiedOrder.setSpbill_create_ip("14.23.150.211");
        unifiedOrder.setTotal_fee(money);

        String sign = createUnifiedOrderSign(unifiedOrder);
        unifiedOrder.setSign(sign);

        *//**
         * 转成XML格式
         *//*
        xmlUtil.getXstreamInclueUnderline().alias("xml", unifiedOrder.getClass());
        String xml = xmlUtil.getXstreamInclueUnderline().toXML(unifiedOrder);

        String response = httpConnection.post(unifiedOrderUrl, xml);
        logger.info("unifiedOrder");
        logger.info(response);
        Map<String, String> responseMap = xmlUtil.parseXml(response);

        return responseMap.get("prepay_id");
    }*/
}

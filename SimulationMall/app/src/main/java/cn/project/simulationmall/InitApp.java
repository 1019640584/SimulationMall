package cn.project.simulationmall;

import android.app.Application;

import org.litepal.LitePal;

import cn.project.simulationmall.bean.Commodity;

public class InitApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        initData();
    }

    /**
     * 初始化所有数据
     */
    private void initData(){
        if (LitePal.findAll(Commodity.class).size()!=0){
            return;
        }
        new Commodity(R.mipmap.img1,"【圣诞礼盒】纪梵希口红迷你5支装组合 304/306 小粉唇01","纪梵希(Givenchy)是来自法国的高奢时装品牌，优美、简洁、典雅是纪梵希最大特点，纪梵希最初以香水为其主要产品，后开始涉足护肤及彩妆事业。在世界品牌实验室编制的2006年度《世界品牌500强》排行榜中名列第411位。2018年12月，世界品牌实验室发布《2018世界品牌500强》榜单，纪梵希排名第433。",300,true).save();
        new Commodity(R.mipmap.img2,"万代模型 1/144 HGBD:R 14芽依 芽衣 创形者 机动人偶机娘 机动人偶芽依5058868","",500,true).save();
        new Commodity(R.mipmap.img3,"学生宿舍床上桌可折叠电脑桌","",150,false).save();
        new Commodity(R.mipmap.img4,"阿玛尼（Emporio Armani）女士项链 镶钻串珠项链","",1314,true).save();
        new Commodity(R.mipmap.img5,"山泽(SAMZHE)Type-C键盘扩展坞","",199,true).save();
        new Commodity(R.mipmap.img6,"七彩虹 Colorful iGame NVLINK HB X4 电竞游戏显卡SLI交火桥接器","",999,true).save();
    }
}

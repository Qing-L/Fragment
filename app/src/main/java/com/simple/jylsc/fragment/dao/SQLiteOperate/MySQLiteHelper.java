package com.simple.jylsc.fragment.dao.SQLiteOperate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/9/14.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    String CREATE_MEMORY = "CREATE TABLE Memory("
            +"id integer primary key autoincrement,"
            +"memoryname text,"
            +"memorycolor text)";
    String CREATE_FRAGMENT = "CREATE TABLE Fragment("
            +"id integer primary key autoincrement,"
            +"title text,"
            +"content text,"
            +"imagepath text,"
            +"createdate text,"
            +"memory_id integer,"
            +"foreign key (memory_id) REFERENCES Memory(id))";
    String INSERT_MEMORY1 = "INSERT INTO Memory values(1,'Food','#f55066');" ;
    String INSERT_MEMORY2 = "INSERT INTO Memory values(2,'Letter','#b8f1ed');" ;
    String INSERT_MEMORY3 = "INSERT INTO Memory values(3,'Famous Place','#e96a25');";
    String INSERT_FRAGMENT1 = "INSERT INTO Fragment values(1,'Noodles','Noodles are an essential ingredient and staple in Chinese " +
            "cuisine. There is a great variety of Chinese noodles, which vary according to their region of production, ingredients," +
            " shape or width, and manner of preparation. They are an important part of most regional cuisines within China, as well as" +
            " in Singapore, and other Southeast Asian nations with sizable overseas Chinese populations.','assets://Image/bg8.jpg','1143882451175',1);" ;
    String INSERT_FRAGMENT2 = "INSERT INTO Fragment values(2,'Rice','Rice is the seed of the grass species Oryza sativa (Asian rice) or Oryza glaberrima (African rice). As a" +
            " cereal grain, it is the most widely consumed staple food for a large part of the world's human population, especially in Asia. It is the agricultural" +
            " commodity with the third-highest worldwide production, after sugarcane and maize, according to 2012 FAOSTAT data.','assets://Image/bg7.jpg','1143888522732',1);" ;
    String INSERT_FRAGMENT3 = "INSERT INTO Fragment values(3,'potato','The potato is a starchy, tuberous crop from the perennial nightshade Solanum tuberosum L. The word" +
            " \"potato\" may refer either to the plant itself or to the edible tuber.[2] In the Andes, where the species is indigenous, there are some other" +
            " closely related cultivated potato species. Potatoes were introduced outside the Andes region approximately four centuries ago,[3] and have since" +
            " become an integral part of much of the world's food supply. It is the world's fourth-largest food crop, following maize, wheat, and rice.'," +
            "'assets://Image/bg3.jpg','1143882451175',1);" ;
    String INSERT_FRAGMENT4 = "INSERT INTO Fragment values(4,'A','A','assets://Image/bg3.jpg','1143882451175',2);" ;
    String INSERT_FRAGMENT5 = "INSERT INTO Fragment values(5,'B','B','assets://Image/bg5.jpg','1143882451175',2);" ;
    String INSERT_FRAGMENT6 = "INSERT INTO Fragment values(6,'C','C','assets://Image/bg4.jpg','1143882451175',2);" ;
    String INSERT_FRAGMENT7 = "INSERT INTO Fragment values(7,'D','D','assets://Image/bg7.jpg','1143882451175',2);";
    String INSERT_FRAGMENT8= "INSERT INTO Fragment values(8,'故宫','北京故宫，旧称为紫禁城，位于北京中轴线的中心，是中国明、清两代24位皇帝的皇家宫殿，" +
            "是中国古代汉族宫廷建筑之精华，无与伦比的建筑杰作，也是世界上现存规模最大、保存最为完整的木质结构的古建筑之一。\n" +
            "北京故宫由明成祖朱棣于永乐四年（公元1406年）开始建设，到明代永乐十八年（公元1420年）建成，占地面积约为72万平方米，" +
            "建筑面积约为15万平方米，宫殿建筑均是木结构、黄琉璃瓦顶、青白石底座。','assets://Image/bg8.jpg','1143882451175',3);" ;
    String INSERT_FRAGMENT9 ="INSERT INTO Fragment values(9,'黄果树瀑布','黄果树瀑布，即黄果树大瀑布。古称白水河瀑布，亦名“黄葛墅”瀑布或“黄桷树”瀑布，" +
            "因本地广泛分布着“黄葛榕”而得名。  位于中国贵州省安顺市镇宁布依族苗族自治县，属珠江水系西江干流南盘江支流北盘江支流" +
            "打帮河的支流可布河下游白水河段水系，为黄果树瀑布群中规模最大的一级瀑布，是世界著名大瀑布之一。以水势浩大著称。瀑布高" +
            "度为77.8米，其中主瀑高67米；瀑布宽101米，其中主瀑顶宽83.3米。黄果树瀑布属喀斯特地貌中的侵蚀裂典型瀑布。'" +
            ",'assets://Image/bg7.jpg','1143882451175',3);";

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MEMORY);
        db.execSQL(CREATE_FRAGMENT);

        db.execSQL(INSERT_MEMORY1);
        db.execSQL(INSERT_MEMORY2);
        db.execSQL(INSERT_MEMORY3);
        db.execSQL(INSERT_FRAGMENT1);
        //db.execSQL(INSERT_FRAGMENT2);
        //db.execSQL(INSERT_FRAGMENT3);
        db.execSQL(INSERT_FRAGMENT4);
        db.execSQL(INSERT_FRAGMENT5);
        db.execSQL(INSERT_FRAGMENT6);
        db.execSQL(INSERT_FRAGMENT7);
        db.execSQL(INSERT_FRAGMENT8);
        db.execSQL(INSERT_FRAGMENT9);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

package com.example.drhyu.learnchinese;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;

import java.util.List;

/**
 * Created by Jaume on 6/22/2016.
 */
public class LoadScreenActivity extends Activity{

    private ChDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_screen_activity_layout);

        datasource = new ChDataSource(this);
        datasource.open();

        //TODO
/*        datasource.removeTable("hsk1");
        datasource.removeTable("hsk2");
        datasource.removeTable("ch_db");*/


        List<TableInfo> tables = datasource.getAllTableNames();


        // Find out if the default set of tables exists
        boolean ch_db,hsk1,hsk2;
        ch_db = hsk1 = hsk2 = false;

        for (int i = 0; i < tables.size(); i++) {
            if(tables.get(i).getTableName().equals("ch_db")) {ch_db = true;}
            else if(tables.get(i).getTableName().equals("hsk1")) {hsk1 = true;}
            else if(tables.get(i).getTableName().equals("hsk2")) {hsk2 = true;}
        }

        // If they don't exist create them
        if(!ch_db){addCH_DB();}
        if(!hsk1){addHSK1();}
        if(!hsk2){addHSK2();}


        Intent i = new Intent();
        i.setClass(LoadScreenActivity.this,test.class);
        startActivity(i);
        LoadScreenActivity.this.finish();
    }
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    private void addHSK1(){

        datasource.createTable("hsk1", ChDataSource.TableType.CHARACTER);

        Character new_char = null;

        new_char= datasource.createCharacter("hsk1","的","de","ind. possession");
        new_char= datasource.createCharacter("hsk1","我","wǒ","I");
        new_char= datasource.createCharacter("hsk1","你","nǐ","you ");
        new_char= datasource.createCharacter("hsk1","是","shì","be");
        new_char= datasource.createCharacter("hsk1","了","le","ind. a completed or finished action");
        new_char= datasource.createCharacter("hsk1","不","bù","no");
        new_char= datasource.createCharacter("hsk1","在","zài","at");
        new_char= datasource.createCharacter("hsk1","他","tā","he");
        new_char= datasource.createCharacter("hsk1","我们","wǒmen","we");
        new_char= datasource.createCharacter("hsk1","好","hǎo","good");
        new_char= datasource.createCharacter("hsk1","有","yǒu","have");
        new_char= datasource.createCharacter("hsk1","这","zhè","this");
        new_char= datasource.createCharacter("hsk1","会","huì","know how to");
        new_char= datasource.createCharacter("hsk1","吗","ma","ind. a yes/no question ");
        new_char= datasource.createCharacter("hsk1","什么","shénme","what? ");
        new_char= datasource.createCharacter("hsk1","说","shuō","speak");
        new_char= datasource.createCharacter("hsk1","她","tā","she");
        new_char= datasource.createCharacter("hsk1","想","xiǎng","think");
        new_char= datasource.createCharacter("hsk1","一","yī","one");
        new_char= datasource.createCharacter("hsk1","很","hěn","very");
        new_char= datasource.createCharacter("hsk1","人","rén","person");
        new_char= datasource.createCharacter("hsk1","那","nà","that");
        new_char= datasource.createCharacter("hsk1","来","lái","come");
        new_char= datasource.createCharacter("hsk1","都","dōu","all");
        new_char= datasource.createCharacter("hsk1","个","ge","general MW.");
        new_char= datasource.createCharacter("hsk1","能","néng","can");
        new_char= datasource.createCharacter("hsk1","去","qù","go");
        new_char= datasource.createCharacter("hsk1","和","hé","and");
        new_char= datasource.createCharacter("hsk1","做","zuò","do");
        new_char= datasource.createCharacter("hsk1","上","shàng","above");
        new_char= datasource.createCharacter("hsk1","没有","méiyǒu","not have");
        new_char= datasource.createCharacter("hsk1","看","kàn","see");
        new_char= datasource.createCharacter("hsk1","怎么","zěnme","how?");
        new_char= datasource.createCharacter("hsk1","现在","xiànzài","now");
        new_char= datasource.createCharacter("hsk1","点","diǎn","a dot");
        new_char= datasource.createCharacter("hsk1","呢","ne","ind. a question");
        new_char= datasource.createCharacter("hsk1","太","tài","too ");
        new_char= datasource.createCharacter("hsk1","里","lǐ","inside");
        new_char= datasource.createCharacter("hsk1","听","tīng","listen");
        new_char= datasource.createCharacter("hsk1","谁","shéi","who");
        new_char= datasource.createCharacter("hsk1","多","duō","many");
        new_char= datasource.createCharacter("hsk1","时候","shíhou","time");
        new_char= datasource.createCharacter("hsk1","下","xià","fall");
        new_char= datasource.createCharacter("hsk1","谢谢","xièxie","thank you");
        new_char= datasource.createCharacter("hsk1","先生","xiānsheng","Mr.");
        new_char= datasource.createCharacter("hsk1","喜欢","xǐhuan","to like");
        new_char= datasource.createCharacter("hsk1","东西","dōngxi","things");
        new_char= datasource.createCharacter("hsk1","小","xiǎo","small");
        new_char= datasource.createCharacter("hsk1","叫","jiào","to be called");
        new_char= datasource.createCharacter("hsk1","爱","ài","love");
        new_char= datasource.createCharacter("hsk1","年","nián","year");
        new_char= datasource.createCharacter("hsk1","请","qǐng","please");
        new_char= datasource.createCharacter("hsk1","回","huí","to return");
        new_char= datasource.createCharacter("hsk1","工作","gōngzuò","work");
        new_char= datasource.createCharacter("hsk1","钱","qián","money");
        new_char= datasource.createCharacter("hsk1","吃","chī","eat");
        new_char= datasource.createCharacter("hsk1","开","kāi","to open");
        new_char= datasource.createCharacter("hsk1","家","jiā","family");
        new_char= datasource.createCharacter("hsk1","哪","nǎa","which");
        new_char= datasource.createCharacter("hsk1","朋友","péngyou","friend");
        new_char= datasource.createCharacter("hsk1","妈妈","māma","mom");
        new_char= datasource.createCharacter("hsk1","今天","jīntiān","today");
        new_char= datasource.createCharacter("hsk1","几","jǐ","how many");
        new_char= datasource.createCharacter("hsk1","爸爸","bàba","Dad");
        new_char= datasource.createCharacter("hsk1","些","xiē","some");
        new_char= datasource.createCharacter("hsk1","怎么样","zěnmeyàng","how about?");
        new_char= datasource.createCharacter("hsk1","对不起","duìbuqǐ","sorry");
        new_char= datasource.createCharacter("hsk1","住","zhù","to live");
        new_char= datasource.createCharacter("hsk1","三","sān","three");
        new_char= datasource.createCharacter("hsk1","高兴","gāoxìng","happy");
        new_char= datasource.createCharacter("hsk1","买","mǎi","to buy");
        new_char= datasource.createCharacter("hsk1","医生","yīshēng","doctor");
        new_char= datasource.createCharacter("hsk1","哪儿","nǎr","where? ");
        new_char= datasource.createCharacter("hsk1","字","zì","letter");
        new_char= datasource.createCharacter("hsk1","名字","míngzi","name");
        new_char= datasource.createCharacter("hsk1","认识","rènshi","recognize");
        new_char= datasource.createCharacter("hsk1","坐","zuò","sit");
        new_char= datasource.createCharacter("hsk1","喝","hē","to drink");
        new_char= datasource.createCharacter("hsk1","写","xiě","to write");
        new_char= datasource.createCharacter("hsk1","月","yuè","moon");
        new_char= datasource.createCharacter("hsk1","号","hào","number");
        new_char= datasource.createCharacter("hsk1","狗","gǒu","dog");
        new_char= datasource.createCharacter("hsk1","岁","suì","years old");
        new_char= datasource.createCharacter("hsk1","看见","kànjiàn","see");
        new_char= datasource.createCharacter("hsk1","喂","wèi","hello ");
        new_char= datasource.createCharacter("hsk1","儿子","érzi","son");
        new_char= datasource.createCharacter("hsk1","漂亮","piàoliang","pretty");
        new_char= datasource.createCharacter("hsk1","分钟","fēnzhōng","minute");
        new_char= datasource.createCharacter("hsk1","再见","zàijiàn","goodbye");
        new_char= datasource.createCharacter("hsk1","本","běn","MW. for books");
        new_char= datasource.createCharacter("hsk1","明天","míngtiān","tomorrow");
        new_char= datasource.createCharacter("hsk1","少","shǎo","few");
        new_char= datasource.createCharacter("hsk1","多少","duōshao","how much?");
        new_char= datasource.createCharacter("hsk1","块","kuài","lump");
        new_char= datasource.createCharacter("hsk1","小姐","xiǎojie","young lady");
        new_char= datasource.createCharacter("hsk1","衣服","yīfu","clothes");
        new_char= datasource.createCharacter("hsk1","水","shuǐ","water");
        new_char= datasource.createCharacter("hsk1","学校","xuéxiào","school");
        new_char= datasource.createCharacter("hsk1","电影","diànyǐng","movie");
        new_char= datasource.createCharacter("hsk1","书","shū","book");
        new_char= datasource.createCharacter("hsk1","四","sì","four");
        new_char= datasource.createCharacter("hsk1","五","wǔ","five");
        new_char= datasource.createCharacter("hsk1","医院","yīyuàn","hospital");
        new_char= datasource.createCharacter("hsk1","飞机","fēijī","airplane");
        new_char= datasource.createCharacter("hsk1","二","èr","two");
        new_char= datasource.createCharacter("hsk1","电视","diànshì","television");
        new_char= datasource.createCharacter("hsk1","读","dú","to read");
        new_char= datasource.createCharacter("hsk1","后面","hòumian","back");
        new_char= datasource.createCharacter("hsk1","昨天","zuótiān","yesterday");
        new_char= datasource.createCharacter("hsk1","六","liù","six");
        new_char= datasource.createCharacter("hsk1","老师","lǎoshī","teacher");
        new_char= datasource.createCharacter("hsk1","星期","xīngqī","week");
        new_char= datasource.createCharacter("hsk1","十","shí","ten");
        new_char= datasource.createCharacter("hsk1","猫","māo","cat");
        new_char= datasource.createCharacter("hsk1","电脑","diànnǎo","computer");
        new_char= datasource.createCharacter("hsk1","热","rè","heat");
        new_char= datasource.createCharacter("hsk1","学生","xuésheng","student");
        new_char= datasource.createCharacter("hsk1","下午","xiàwǔ","afternoon");
        new_char= datasource.createCharacter("hsk1","学习","xuéxí","learn");
        new_char= datasource.createCharacter("hsk1","冷","lěng","cold");
        new_char= datasource.createCharacter("hsk1","前面","qiánmiàn","in front");
        new_char= datasource.createCharacter("hsk1","八","bā","eight");
        new_char= datasource.createCharacter("hsk1","中国","Zhōngguó","China");
        new_char= datasource.createCharacter("hsk1","七","qī","seven");
        new_char= datasource.createCharacter("hsk1","菜","cài","dish ");
        new_char= datasource.createCharacter("hsk1","桌子","zhuōzi","table");
        new_char= datasource.createCharacter("hsk1","出租车","chūzūchē","taxi");
        new_char= datasource.createCharacter("hsk1","天气","tiānqì","weather");
        new_char= datasource.createCharacter("hsk1","茶","chá","tea");
        new_char= datasource.createCharacter("hsk1","九","jiǔ","nine");
        new_char= datasource.createCharacter("hsk1","商店","shāngdiàn","shop");
        new_char= datasource.createCharacter("hsk1","椅子","yǐzi","chair");
        new_char= datasource.createCharacter("hsk1","同学","tóngxué","fellow student");
        new_char= datasource.createCharacter("hsk1","一点儿","yìdiǎnr","a bit");
        new_char= datasource.createCharacter("hsk1","苹果","píngguǒ","apple");
        new_char= datasource.createCharacter("hsk1","饭店","fàndiàn","restaurant");
        new_char= datasource.createCharacter("hsk1","中午","zhōngwǔ","noon");
        new_char= datasource.createCharacter("hsk1","上午","shàngwǔ","late morning ");
        new_char= datasource.createCharacter("hsk1","水果","shuǐguǒ","fruit");
        new_char= datasource.createCharacter("hsk1","杯子","bēizi","cup");
        new_char= datasource.createCharacter("hsk1","下雨","xiàyǔ","to rain");
        new_char= datasource.createCharacter("hsk1","北京","Běijīng","Beijing");
        new_char= datasource.createCharacter("hsk1","汉语","Hànyǔ","Chinese language");
    }

    private void addHSK2(){

        datasource.createTable("hsk2", ChDataSource.TableType.CHARACTER);

        Character new_char = null;
        //TODO
        new_char= datasource.createCharacter("hsk2","的","de","ind. possession");

        new_char= datasource.createCharacter("hsk2","就","jiù","then");
        new_char= datasource.createCharacter("hsk2","知道","zhīdao","know");
        new_char= datasource.createCharacter("hsk2","到","dào","arrive ");
        new_char= datasource.createCharacter("hsk2","对","duì","correct");
        new_char= datasource.createCharacter("hsk2","也","yě","also");
        new_char= datasource.createCharacter("hsk2","让","ràng","ask");
        new_char= datasource.createCharacter("hsk2","过","guò","to pass");
        new_char= datasource.createCharacter("hsk2","真","zhēn","real");
        new_char= datasource.createCharacter("hsk2","可以","kěyǐ","can");
        new_char= datasource.createCharacter("hsk2","走","zǒu","to walk");
        new_char= datasource.createCharacter("hsk2","告诉","gàosu","to tell");
        new_char= datasource.createCharacter("hsk2","因为","yīnwèi","because");
        new_char= datasource.createCharacter("hsk2","再","zài","again");
        new_char= datasource.createCharacter("hsk2","快","kuài","fast");
        new_char= datasource.createCharacter("hsk2","但是","dànshì","but");
        new_char= datasource.createCharacter("hsk2","已经","yǐjing","already");
        new_char= datasource.createCharacter("hsk2","为什么","wèishénme","why?");
        new_char= datasource.createCharacter("hsk2","觉得","juéde","feel");
        new_char= datasource.createCharacter("hsk2","它","tā","it");
        new_char= datasource.createCharacter("hsk2","从","cóng","from");
        new_char= datasource.createCharacter("hsk2","找","zhǎo","try to find");
        new_char= datasource.createCharacter("hsk2","最","zuì","the most");
        new_char= datasource.createCharacter("hsk2","可能","kěnéng","possible");
        new_char= datasource.createCharacter("hsk2","出","chū","go out");
        new_char= datasource.createCharacter("hsk2","孩子","háizi","child");
        new_char= datasource.createCharacter("hsk2","所以","suǒyǐ","so");
        new_char= datasource.createCharacter("hsk2","两","liǎng","two");
        new_char= datasource.createCharacter("hsk2","错","cuò","mistake");
        new_char= datasource.createCharacter("hsk2","等","děng","to wait");
        new_char= datasource.createCharacter("hsk2","题","tí","topic");
        new_char= datasource.createCharacter("hsk2","问","wèn","ask");
        new_char= datasource.createCharacter("hsk2","问题","wèntí","question");
        new_char= datasource.createCharacter("hsk2","一起","yìqǐ","together");
        new_char= datasource.createCharacter("hsk2","开始","kāishǐ","begin");
        new_char= datasource.createCharacter("hsk2","时间","shíjiān","time");
        new_char= datasource.createCharacter("hsk2","事情","shìqing","matter");
        new_char= datasource.createCharacter("hsk2","一下","yíxià","a little bit/while");
        new_char= datasource.createCharacter("hsk2","非常","fēicháng","extremely");
        new_char= datasource.createCharacter("hsk2","希望","xīwàng","to hope");
        new_char= datasource.createCharacter("hsk2","准备","zhǔnbèi","prepare");
        new_char= datasource.createCharacter("hsk2","比","bǐ","compare");
        new_char= datasource.createCharacter("hsk2","意思","yìsi","meaning");
        new_char= datasource.createCharacter("hsk2","第一","dìyī","first");
        new_char= datasource.createCharacter("hsk2","进","jìn","enter");
        new_char= datasource.createCharacter("hsk2","大家","dàjiā","everyone");
        new_char= datasource.createCharacter("hsk2","新","xīn","new");
        new_char= datasource.createCharacter("hsk2","您","nín","you ");
        new_char= datasource.createCharacter("hsk2","穿","chuān","to wear");
        new_char= datasource.createCharacter("hsk2","送","sòng","deliver");
        new_char= datasource.createCharacter("hsk2","玩","wán","to play");
        new_char= datasource.createCharacter("hsk2","小时","xiǎoshí","hour");
        new_char= datasource.createCharacter("hsk2","完","wán","to finish");
        new_char= datasource.createCharacter("hsk2","每","měi","each");
        new_char= datasource.createCharacter("hsk2","公司","gōngsī","company");
        new_char= datasource.createCharacter("hsk2","帮助","bāngzhù","help");
        new_char= datasource.createCharacter("hsk2","晚上","wǎnshang","evening");
        new_char= datasource.createCharacter("hsk2","说话","shuōhuà","to talk");
        new_char= datasource.createCharacter("hsk2","门","mén","door");
        new_char= datasource.createCharacter("hsk2","女","nǚ","woman");
        new_char= datasource.createCharacter("hsk2","忙","máng","busy");
        new_char= datasource.createCharacter("hsk2","卖","mài","to sell");
        new_char= datasource.createCharacter("hsk2","高","gāo","high");
        new_char= datasource.createCharacter("hsk2","房间","fángjiān","room");
        new_char= datasource.createCharacter("hsk2","路","lù","road");
        new_char= datasource.createCharacter("hsk2","懂","dǒng","understand");
        new_char= datasource.createCharacter("hsk2","正在","zhèngzài","in the process of ");
        new_char= datasource.createCharacter("hsk2","笑","xiào","to laugh");
        new_char= datasource.createCharacter("hsk2","远","yuǎn","far");
        new_char= datasource.createCharacter("hsk2","妻子","qīzi","wife");
        new_char= datasource.createCharacter("hsk2","丈夫","zhàngfu","husband");
        new_char= datasource.createCharacter("hsk2","离","lí","leave");
        new_char= datasource.createCharacter("hsk2","往","wǎng","to go ");
        new_char= datasource.createCharacter("hsk2","男","nán","male");
        new_char= datasource.createCharacter("hsk2","眼睛","yǎnjing","eye");
        new_char= datasource.createCharacter("hsk2","快乐","kuàilè","happy");
        new_char= datasource.createCharacter("hsk2","虽然","suīrán","although");
        new_char= datasource.createCharacter("hsk2","药","yào","medicine");
        new_char= datasource.createCharacter("hsk2","身体","shēntǐ","health");
        new_char= datasource.createCharacter("hsk2","黑","hēi","black");
        new_char= datasource.createCharacter("hsk2","咖啡","kāfēi","coffee");
        new_char= datasource.createCharacter("hsk2","日","rì","sun");
        new_char= datasource.createCharacter("hsk2","休息","xiūxi","to rest");
        new_char= datasource.createCharacter("hsk2","外","wài","outer");
        new_char= datasource.createCharacter("hsk2","生日","shēngrì","birthday");
        new_char= datasource.createCharacter("hsk2","哥哥","gēge","older brother");
        new_char= datasource.createCharacter("hsk2","票","piào","ticket");
        new_char= datasource.createCharacter("hsk2","手机","shǒujī","mobile ");
        new_char= datasource.createCharacter("hsk2","洗","xǐ","to wash");
        new_char= datasource.createCharacter("hsk2","弟弟","dìdi","younger brother");
        new_char= datasource.createCharacter("hsk2","妹妹","mèimei","younger sister");
        new_char= datasource.createCharacter("hsk2","红","hóng","red");
        new_char= datasource.createCharacter("hsk2","慢","màn","slow");
        new_char= datasource.createCharacter("hsk2","近","jìn","near");
        new_char= datasource.createCharacter("hsk2","姐姐","jiějie","older sister");
        new_char= datasource.createCharacter("hsk2","介绍","jièshào","to introduce");
        new_char= datasource.createCharacter("hsk2","鱼","yú","fish ");
        new_char= datasource.createCharacter("hsk2","课","kè","class");
        new_char= datasource.createCharacter("hsk2","旁边","pángbiān","side");
        new_char= datasource.createCharacter("hsk2","去年","qùnián","last year");
        new_char= datasource.createCharacter("hsk2","报纸","bàozhǐ","newspaper");
        new_char= datasource.createCharacter("hsk2","颜色","yánsè","color");
        new_char= datasource.createCharacter("hsk2","机场","jīchǎng","airport");
        new_char= datasource.createCharacter("hsk2","唱歌","chànggē","sing ");
        new_char= datasource.createCharacter("hsk2","千","qiān","one thousand");
        new_char= datasource.createCharacter("hsk2","好吃","hǎochī","tasty");
        new_char= datasource.createCharacter("hsk2","考试","kǎoshì","ModidyListActivity");
        new_char= datasource.createCharacter("hsk2","左边","zuǒbian","the left side");
        new_char= datasource.createCharacter("hsk2","姓","xìng","surname");
        new_char= datasource.createCharacter("hsk2","百","bǎi","hundred");
        new_char= datasource.createCharacter("hsk2","雪","xuě","snow");
        new_char= datasource.createCharacter("hsk2","贵","guì","expensive");
        new_char= datasource.createCharacter("hsk2","游泳","yóuyǒng","to swim");
        new_char= datasource.createCharacter("hsk2","牛奶","niúnǎi","cow's milk");
        new_char= datasource.createCharacter("hsk2","右边","yòubian","the right ");
        new_char= datasource.createCharacter("hsk2","便宜","piányi","cheap");
        new_char= datasource.createCharacter("hsk2","踢足球","tīzúqiú","to play football/soccer");
        new_char= datasource.createCharacter("hsk2","零","líng","zero");
        new_char= datasource.createCharacter("hsk2","手表","shǒubiǎo","wristwatch");
        new_char= datasource.createCharacter("hsk2","旅游","lǚyóu","trip");
        new_char= datasource.createCharacter("hsk2","服务员","fúwùyuán","waiter/waitress");
        new_char= datasource.createCharacter("hsk2","宾馆","bīnguǎn","guesthouse");
        new_char= datasource.createCharacter("hsk2","教室","jiàoshì","classroom");
        new_char= datasource.createCharacter("hsk2","阴","yīn","cloudy ");
        new_char= datasource.createCharacter("hsk2","面条","miàntiáo","noodles");
        new_char= datasource.createCharacter("hsk2","铅笔","qiānbǐ","pencil");
        new_char= datasource.createCharacter("hsk2","火车站","huǒchēzhàn","train station");
        new_char= datasource.createCharacter("hsk2","西瓜","xīguā","watermelon");
        new_char= datasource.createCharacter("hsk2","羊肉","yángròu","mutton");
        new_char= datasource.createCharacter("hsk2","晴","qíng","clear");
    }

    private void addCH_DB(){
        datasource.createTable("ch_db", ChDataSource.TableType.CHARACTER_HISTORY);
        
       /* Character new_char = null;

        new_char= datasource.createCharacterStatistics("的","de","ind. possession");
        new_char= datasource.createCharacterStatistics("的","de","ind. possession");
        new_char= datasource.createCharacterStatistics("我","wǒ","I");
        new_char= datasource.createCharacterStatistics("你","nǐ","you ");
        new_char= datasource.createCharacterStatistics("是","shì","be");
        new_char= datasource.createCharacterStatistics("了","le","ind. a completed or finished action");
        new_char= datasource.createCharacterStatistics("不","bù","no");
        new_char= datasource.createCharacterStatistics("在","zài","at");
        new_char= datasource.createCharacterStatistics("他","tā","he");
        new_char= datasource.createCharacterStatistics("我们","wǒmen","we");
        new_char= datasource.createCharacterStatistics("好","hǎo","good");
        new_char= datasource.createCharacterStatistics("有","yǒu","have");
        new_char= datasource.createCharacterStatistics("这","zhè","this");
        new_char= datasource.createCharacterStatistics("会","huì","know how to");
        new_char= datasource.createCharacterStatistics("吗","ma","ind. a yes/no question ");
        new_char= datasource.createCharacterStatistics("什么","shénme","what? ");
        new_char= datasource.createCharacterStatistics("说","shuō","speak");
        new_char= datasource.createCharacterStatistics("她","tā","she");
        new_char= datasource.createCharacterStatistics("想","xiǎng","think");
        new_char= datasource.createCharacterStatistics("一","yī","one");
        new_char= datasource.createCharacterStatistics("很","hěn","very");
        new_char= datasource.createCharacterStatistics("人","rén","person");
        new_char= datasource.createCharacterStatistics("那","nà","that");
        new_char= datasource.createCharacterStatistics("来","lái","come");
        new_char= datasource.createCharacterStatistics("都","dōu","all");
        new_char= datasource.createCharacterStatistics("个","ge","general MW.");
        new_char= datasource.createCharacterStatistics("能","néng","can");
        new_char= datasource.createCharacterStatistics("去","qù","go");
        new_char= datasource.createCharacterStatistics("和","hé","and");
        new_char= datasource.createCharacterStatistics("做","zuò","do");
        new_char= datasource.createCharacterStatistics("上","shàng","above");
        new_char= datasource.createCharacterStatistics("没有","méiyǒu","not have");
        new_char= datasource.createCharacterStatistics("看","kàn","see");
        new_char= datasource.createCharacterStatistics("怎么","zěnme","how?");
        new_char= datasource.createCharacterStatistics("现在","xiànzài","now");
        new_char= datasource.createCharacterStatistics("点","diǎn","a dot");
        new_char= datasource.createCharacterStatistics("呢","ne","ind. a question");
        new_char= datasource.createCharacterStatistics("太","tài","too ");
        new_char= datasource.createCharacterStatistics("里","lǐ","inside");
        new_char= datasource.createCharacterStatistics("听","tīng","listen");
        new_char= datasource.createCharacterStatistics("谁","shéi","who");
        new_char= datasource.createCharacterStatistics("多","duō","many");
        new_char= datasource.createCharacterStatistics("时候","shíhou","time");
        new_char= datasource.createCharacterStatistics("下","xià","fall");
        new_char= datasource.createCharacterStatistics("谢谢","xièxie","thank you");
        new_char= datasource.createCharacterStatistics("先生","xiānsheng","Mr.");
        new_char= datasource.createCharacterStatistics("喜欢","xǐhuan","to like");
        new_char= datasource.createCharacterStatistics("东西","dōngxi","things");
        new_char= datasource.createCharacterStatistics("小","xiǎo","small");
        new_char= datasource.createCharacterStatistics("叫","jiào","to be called");
        new_char= datasource.createCharacterStatistics("爱","ài","love");
        new_char= datasource.createCharacterStatistics("年","nián","year");
        new_char= datasource.createCharacterStatistics("请","qǐng","please");
        new_char= datasource.createCharacterStatistics("回","huí","to return");
        new_char= datasource.createCharacterStatistics("工作","gōngzuò","work");
        new_char= datasource.createCharacterStatistics("钱","qián","money");
        new_char= datasource.createCharacterStatistics("吃","chī","eat");
        new_char= datasource.createCharacterStatistics("开","kāi","to open");
        new_char= datasource.createCharacterStatistics("家","jiā","family");
        new_char= datasource.createCharacterStatistics("哪","nǎa","which");
        new_char= datasource.createCharacterStatistics("朋友","péngyou","friend");
        new_char= datasource.createCharacterStatistics("妈妈","māma","mom");
        new_char= datasource.createCharacterStatistics("今天","jīntiān","today");
        new_char= datasource.createCharacterStatistics("几","jǐ","how many");
        new_char= datasource.createCharacterStatistics("爸爸","bàba","Dad");
        new_char= datasource.createCharacterStatistics("些","xiē","some");
        new_char= datasource.createCharacterStatistics("怎么样","zěnmeyàng","how about?");
        new_char= datasource.createCharacterStatistics("对不起","duìbuqǐ","sorry");
        new_char= datasource.createCharacterStatistics("住","zhù","to live");
        new_char= datasource.createCharacterStatistics("三","sān","three");
        new_char= datasource.createCharacterStatistics("高兴","gāoxìng","happy");
        new_char= datasource.createCharacterStatistics("买","mǎi","to buy");
        new_char= datasource.createCharacterStatistics("医生","yīshēng","doctor");
        new_char= datasource.createCharacterStatistics("哪儿","nǎr","where? ");
        new_char= datasource.createCharacterStatistics("字","zì","letter");
        new_char= datasource.createCharacterStatistics("名字","míngzi","name");
        new_char= datasource.createCharacterStatistics("认识","rènshi","recognize");
        new_char= datasource.createCharacterStatistics("坐","zuò","sit");
        new_char= datasource.createCharacterStatistics("喝","hē","to drink");
        new_char= datasource.createCharacterStatistics("写","xiě","to write");
        new_char= datasource.createCharacterStatistics("月","yuè","moon");
        new_char= datasource.createCharacterStatistics("号","hào","number");
        new_char= datasource.createCharacterStatistics("狗","gǒu","dog");
        new_char= datasource.createCharacterStatistics("岁","suì","years old");
        new_char= datasource.createCharacterStatistics("看见","kànjiàn","see");
        new_char= datasource.createCharacterStatistics("喂","wèi","hello ");
        new_char= datasource.createCharacterStatistics("儿子","érzi","son");
        new_char= datasource.createCharacterStatistics("漂亮","piàoliang","pretty");
        new_char= datasource.createCharacterStatistics("分钟","fēnzhōng","minute");
        new_char= datasource.createCharacterStatistics("再见","zàijiàn","goodbye");
        new_char= datasource.createCharacterStatistics("本","běn","MW. for books");
        new_char= datasource.createCharacterStatistics("明天","míngtiān","tomorrow");
        new_char= datasource.createCharacterStatistics("少","shǎo","few");
        new_char= datasource.createCharacterStatistics("多少","duōshao","how much?");
        new_char= datasource.createCharacterStatistics("块","kuài","lump");
        new_char= datasource.createCharacterStatistics("小姐","xiǎojie","young lady");
        new_char= datasource.createCharacterStatistics("衣服","yīfu","clothes");
        new_char= datasource.createCharacterStatistics("水","shuǐ","water");
        new_char= datasource.createCharacterStatistics("学校","xuéxiào","school");
        new_char= datasource.createCharacterStatistics("电影","diànyǐng","movie");
        new_char= datasource.createCharacterStatistics("书","shū","book");
        new_char= datasource.createCharacterStatistics("四","sì","four");
        new_char= datasource.createCharacterStatistics("五","wǔ","five");
        new_char= datasource.createCharacterStatistics("医院","yīyuàn","hospital");
        new_char= datasource.createCharacterStatistics("飞机","fēijī","airplane");
        new_char= datasource.createCharacterStatistics("二","èr","two");
        new_char= datasource.createCharacterStatistics("电视","diànshì","television");
        new_char= datasource.createCharacterStatistics("读","dú","to read");
        new_char= datasource.createCharacterStatistics("后面","hòumian","back");
        new_char= datasource.createCharacterStatistics("昨天","zuótiān","yesterday");
        new_char= datasource.createCharacterStatistics("六","liù","six");
        new_char= datasource.createCharacterStatistics("老师","lǎoshī","teacher");
        new_char= datasource.createCharacterStatistics("星期","xīngqī","week");
        new_char= datasource.createCharacterStatistics("十","shí","ten");
        new_char= datasource.createCharacterStatistics("猫","māo","cat");
        new_char= datasource.createCharacterStatistics("电脑","diànnǎo","computer");
        new_char= datasource.createCharacterStatistics("热","rè","heat");
        new_char= datasource.createCharacterStatistics("学生","xuésheng","student");
        new_char= datasource.createCharacterStatistics("下午","xiàwǔ","afternoon");
        new_char= datasource.createCharacterStatistics("学习","xuéxí","learn");
        new_char= datasource.createCharacterStatistics("冷","lěng","cold");
        new_char= datasource.createCharacterStatistics("前面","qiánmiàn","in front");
        new_char= datasource.createCharacterStatistics("八","bā","eight");
        new_char= datasource.createCharacterStatistics("中国","Zhōngguó","China");
        new_char= datasource.createCharacterStatistics("七","qī","seven");
        new_char= datasource.createCharacterStatistics("菜","cài","dish ");
        new_char= datasource.createCharacterStatistics("桌子","zhuōzi","table");
        new_char= datasource.createCharacterStatistics("出租车","chūzūchē","taxi");
        new_char= datasource.createCharacterStatistics("天气","tiānqì","weather");
        new_char= datasource.createCharacterStatistics("茶","chá","tea");
        new_char= datasource.createCharacterStatistics("九","jiǔ","nine");
        new_char= datasource.createCharacterStatistics("商店","shāngdiàn","shop");
        new_char= datasource.createCharacterStatistics("椅子","yǐzi","chair");
        new_char= datasource.createCharacterStatistics("同学","tóngxué","fellow student");
        new_char= datasource.createCharacterStatistics("一点儿","yìdiǎnr","a bit");
        new_char= datasource.createCharacterStatistics("苹果","píngguǒ","apple");
        new_char= datasource.createCharacterStatistics("饭店","fàndiàn","restaurant");
        new_char= datasource.createCharacterStatistics("中午","zhōngwǔ","noon");
        new_char= datasource.createCharacterStatistics("上午","shàngwǔ","late morning ");
        new_char= datasource.createCharacterStatistics("水果","shuǐguǒ","fruit");
        new_char= datasource.createCharacterStatistics("杯子","bēizi","cup");
        new_char= datasource.createCharacterStatistics("下雨","xiàyǔ","to rain");
        new_char= datasource.createCharacterStatistics("北京","Běijīng","Beijing");
        new_char= datasource.createCharacterStatistics("汉语","Hànyǔ","Chinese language");
        new_char= datasource.createCharacterStatistics("就","jiù","then");
        new_char= datasource.createCharacterStatistics("知道","zhīdao","know");
        new_char= datasource.createCharacterStatistics("到","dào","arrive ");
        new_char= datasource.createCharacterStatistics("对","duì","correct");
        new_char= datasource.createCharacterStatistics("也","yě","also");
        new_char= datasource.createCharacterStatistics("让","ràng","ask");
        new_char= datasource.createCharacterStatistics("过","guò","to pass");
        new_char= datasource.createCharacterStatistics("真","zhēn","real");
        new_char= datasource.createCharacterStatistics("可以","kěyǐ","can");
        new_char= datasource.createCharacterStatistics("走","zǒu","to walk");
        new_char= datasource.createCharacterStatistics("告诉","gàosu","to tell");
        new_char= datasource.createCharacterStatistics("因为","yīnwèi","because");
        new_char= datasource.createCharacterStatistics("再","zài","again");
        new_char= datasource.createCharacterStatistics("快","kuài","fast");
        new_char= datasource.createCharacterStatistics("但是","dànshì","but");
        new_char= datasource.createCharacterStatistics("已经","yǐjing","already");
        new_char= datasource.createCharacterStatistics("为什么","wèishénme","why?");
        new_char= datasource.createCharacterStatistics("觉得","juéde","feel");
        new_char= datasource.createCharacterStatistics("它","tā","it");
        new_char= datasource.createCharacterStatistics("从","cóng","from");
        new_char= datasource.createCharacterStatistics("找","zhǎo","try to find");
        new_char= datasource.createCharacterStatistics("最","zuì","the most");
        new_char= datasource.createCharacterStatistics("可能","kěnéng","possible");
        new_char= datasource.createCharacterStatistics("出","chū","go out");
        new_char= datasource.createCharacterStatistics("孩子","háizi","child");
        new_char= datasource.createCharacterStatistics("所以","suǒyǐ","so");
        new_char= datasource.createCharacterStatistics("两","liǎng","two");
        new_char= datasource.createCharacterStatistics("错","cuò","mistake");
        new_char= datasource.createCharacterStatistics("等","děng","to wait");
        new_char= datasource.createCharacterStatistics("题","tí","topic");
        new_char= datasource.createCharacterStatistics("问","wèn","ask");
        new_char= datasource.createCharacterStatistics("问题","wèntí","question");
        new_char= datasource.createCharacterStatistics("一起","yìqǐ","together");
        new_char= datasource.createCharacterStatistics("开始","kāishǐ","begin");
        new_char= datasource.createCharacterStatistics("时间","shíjiān","time");
        new_char= datasource.createCharacterStatistics("事情","shìqing","matter");
        new_char= datasource.createCharacterStatistics("一下","yíxià","a little bit/while");
        new_char= datasource.createCharacterStatistics("非常","fēicháng","extremely");
        new_char= datasource.createCharacterStatistics("希望","xīwàng","to hope");
        new_char= datasource.createCharacterStatistics("准备","zhǔnbèi","prepare");
        new_char= datasource.createCharacterStatistics("比","bǐ","compare");
        new_char= datasource.createCharacterStatistics("意思","yìsi","meaning");
        new_char= datasource.createCharacterStatistics("第一","dìyī","first");
        new_char= datasource.createCharacterStatistics("进","jìn","enter");
        new_char= datasource.createCharacterStatistics("大家","dàjiā","everyone");
        new_char= datasource.createCharacterStatistics("新","xīn","new");
        new_char= datasource.createCharacterStatistics("您","nín","you ");
        new_char= datasource.createCharacterStatistics("穿","chuān","to wear");
        new_char= datasource.createCharacterStatistics("送","sòng","deliver");
        new_char= datasource.createCharacterStatistics("玩","wán","to play");
        new_char= datasource.createCharacterStatistics("小时","xiǎoshí","hour");
        new_char= datasource.createCharacterStatistics("完","wán","to finish");
        new_char= datasource.createCharacterStatistics("每","měi","each");
        new_char= datasource.createCharacterStatistics("公司","gōngsī","company");
        new_char= datasource.createCharacterStatistics("帮助","bāngzhù","help");
        new_char= datasource.createCharacterStatistics("晚上","wǎnshang","evening");
        new_char= datasource.createCharacterStatistics("说话","shuōhuà","to talk");
        new_char= datasource.createCharacterStatistics("门","mén","door");
        new_char= datasource.createCharacterStatistics("女","nǚ","woman");
        new_char= datasource.createCharacterStatistics("忙","máng","busy");
        new_char= datasource.createCharacterStatistics("卖","mài","to sell");
        new_char= datasource.createCharacterStatistics("高","gāo","high");
        new_char= datasource.createCharacterStatistics("房间","fángjiān","room");
        new_char= datasource.createCharacterStatistics("路","lù","road");
        new_char= datasource.createCharacterStatistics("懂","dǒng","understand");
        new_char= datasource.createCharacterStatistics("正在","zhèngzài","in the process of ");
        new_char= datasource.createCharacterStatistics("笑","xiào","to laugh");
        new_char= datasource.createCharacterStatistics("远","yuǎn","far");
        new_char= datasource.createCharacterStatistics("妻子","qīzi","wife");
        new_char= datasource.createCharacterStatistics("丈夫","zhàngfu","husband");
        new_char= datasource.createCharacterStatistics("离","lí","leave");
        new_char= datasource.createCharacterStatistics("往","wǎng","to go ");
        new_char= datasource.createCharacterStatistics("男","nán","male");
        new_char= datasource.createCharacterStatistics("眼睛","yǎnjing","eye");
        new_char= datasource.createCharacterStatistics("快乐","kuàilè","happy");
        new_char= datasource.createCharacterStatistics("虽然","suīrán","although");
        new_char= datasource.createCharacterStatistics("药","yào","medicine");
        new_char= datasource.createCharacterStatistics("身体","shēntǐ","health");
        new_char= datasource.createCharacterStatistics("黑","hēi","black");
        new_char= datasource.createCharacterStatistics("咖啡","kāfēi","coffee");
        new_char= datasource.createCharacterStatistics("日","rì","sun");
        new_char= datasource.createCharacterStatistics("休息","xiūxi","to rest");
        new_char= datasource.createCharacterStatistics("外","wài","outer");
        new_char= datasource.createCharacterStatistics("生日","shēngrì","birthday");
        new_char= datasource.createCharacterStatistics("哥哥","gēge","older brother");
        new_char= datasource.createCharacterStatistics("票","piào","ticket");
        new_char= datasource.createCharacterStatistics("手机","shǒujī","mobile ");
        new_char= datasource.createCharacterStatistics("洗","xǐ","to wash");
        new_char= datasource.createCharacterStatistics("弟弟","dìdi","younger brother");
        new_char= datasource.createCharacterStatistics("妹妹","mèimei","younger sister");
        new_char= datasource.createCharacterStatistics("红","hóng","red");
        new_char= datasource.createCharacterStatistics("慢","màn","slow");
        new_char= datasource.createCharacterStatistics("近","jìn","near");
        new_char= datasource.createCharacterStatistics("姐姐","jiějie","older sister");
        new_char= datasource.createCharacterStatistics("介绍","jièshào","to introduce");
        new_char= datasource.createCharacterStatistics("鱼","yú","fish ");
        new_char= datasource.createCharacterStatistics("课","kè","class");
        new_char= datasource.createCharacterStatistics("旁边","pángbiān","side");
        new_char= datasource.createCharacterStatistics("去年","qùnián","last year");
        new_char= datasource.createCharacterStatistics("报纸","bàozhǐ","newspaper");
        new_char= datasource.createCharacterStatistics("颜色","yánsè","color");
        new_char= datasource.createCharacterStatistics("机场","jīchǎng","airport");
        new_char= datasource.createCharacterStatistics("唱歌","chànggē","sing ");
        new_char= datasource.createCharacterStatistics("千","qiān","one thousand");
        new_char= datasource.createCharacterStatistics("好吃","hǎochī","tasty");
        new_char= datasource.createCharacterStatistics("考试","kǎoshì","ModidyListActivity");
        new_char= datasource.createCharacterStatistics("左边","zuǒbian","the left side");
        new_char= datasource.createCharacterStatistics("姓","xìng","surname");
        new_char= datasource.createCharacterStatistics("百","bǎi","hundred");
        new_char= datasource.createCharacterStatistics("雪","xuě","snow");
        new_char= datasource.createCharacterStatistics("贵","guì","expensive");
        new_char= datasource.createCharacterStatistics("游泳","yóuyǒng","to swim");
        new_char= datasource.createCharacterStatistics("牛奶","niúnǎi","cow's milk");
        new_char= datasource.createCharacterStatistics("右边","yòubian","the right ");
        new_char= datasource.createCharacterStatistics("便宜","piányi","cheap");
        new_char= datasource.createCharacterStatistics("踢足球","tīzúqiú","to play football/soccer");
        new_char= datasource.createCharacterStatistics("零","líng","zero");
        new_char= datasource.createCharacterStatistics("手表","shǒubiǎo","wristwatch");
        new_char= datasource.createCharacterStatistics("旅游","lǚyóu","trip");
        new_char= datasource.createCharacterStatistics("服务员","fúwùyuán","waiter/waitress");
        new_char= datasource.createCharacterStatistics("宾馆","bīnguǎn","guesthouse");
        new_char= datasource.createCharacterStatistics("教室","jiàoshì","classroom");
        new_char= datasource.createCharacterStatistics("阴","yīn","cloudy ");
        new_char= datasource.createCharacterStatistics("面条","miàntiáo","noodles");
        new_char= datasource.createCharacterStatistics("铅笔","qiānbǐ","pencil");
        new_char= datasource.createCharacterStatistics("火车站","huǒchēzhàn","train station");
        new_char= datasource.createCharacterStatistics("西瓜","xīguā","watermelon");
        new_char= datasource.createCharacterStatistics("羊肉","yángròu","mutton");
        new_char= datasource.createCharacterStatistics("晴","qíng","clear");*/


    }
}



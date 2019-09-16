package com.example.ren.reading;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


//일단 디비 단위에서만 페이지 integer로 받고 리스트뷰에선 String으로 받음 필요시 고침

public class BookDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "book.db";
    public static final String BOOK_TABLE_NAME = "book";
    public static final String BOOK_COLUMN_ID = "_id";
    public static final String BOOK_COLUMN_BOOKNAME = "bookname";
    public static final String BOOK_COLUMN_WRITER = "writer";
    public static final String BOOK_COLUMN_PUBLISHER = "publisher";
    public static final String BOOK_COLUMN_PAGE = "page";
    public static final String BOOK_COLUMN_GENRE = "genre";
    public static final String BOOK_COLUMN_ISBN = "isbn";


    public BookDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  " +           BOOK_TABLE_NAME+
                "  ( " + BOOK_COLUMN_ID +" integer primary key AUTOINCREMENT NOT NULL,"+  BOOK_COLUMN_ISBN+
                " Text,"+                    BOOK_COLUMN_BOOKNAME+
                " Text,"+                    BOOK_COLUMN_WRITER+
                " Text,"+                    BOOK_COLUMN_PUBLISHER+
                " Text,"+                    BOOK_COLUMN_PAGE+
                " Text,"+                    BOOK_COLUMN_GENRE+
                " Text)"
        );

        db.execSQL("INSERT INTO book VALUES(null,'9788950963101','3년 후, 한국은 없다','공병호','21세기북스','224','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788950962395','인생교과서 간디(플라톤 아카데미 총서)','류성민','21세기북스','96','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788950962906','어떻게 다시 성장할 것인가','박광기','21세기북스','96','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788950962814','초등 마음 처방전','이서윤','21세기북스','160','가정/생활');");
        db.execSQL("INSERT INTO book VALUES(null,'9788950962791','초등 입학 처방전','이서윤','21세기북스','160','가정/생활');");
        db.execSQL("INSERT INTO book VALUES(null,'9788950962807','초등 학습 처방전','이서윤','21세기북스','152','가정/생활');");
        db.execSQL("INSERT INTO book VALUES(null,'9788950962593','우리집 테라스에 펭귄이 산다(양장본 HardCover)','톰 미첼','21세기북스','152','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788962183764','심리학 연구방법(9판)','David G. Elmes','Cengage Learning','1002','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788968441141','조직이론과 설계(12판)','Richard L. Daft','Cengage Learning','330','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788996464976','특허영어. 8(Puzzle처럼 쉬운)','임국준','DETA 어학연구소','346','외국어');");
        db.execSQL("INSERT INTO book VALUES(null,'9788959793501','천재들의 과학노트. 2: 화학','캐서린 쿨렌','Gbrain','428','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788959793518','천재들의 과학노트. 3: 물리학','캐서린 쿨렌','Gbrain','372','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788996454663','행복 DNA(나를 깨우는)','봉성훈','GNPBOOKS','514','자기계발');");
        db.execSQL("INSERT INTO book VALUES(null,'9788989884910','맹자여행기(책 밖으로 나온 사상가 1)','신정근','h2(에이치투)','521','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788976418838','Rise of Korean Language Programs in U.S. Institute of Higher Education(양장본 HardCover)','Hye-Sook Wang','Korea University Press','468','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9791132100959','마케팅(4판)(양장본 HardCover)','Dhruv Grewal','McGraw-Hill Education','224','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9791132100904','미시경제학(9판)','Robert H. Frank','McGraw-Hill Education','628','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788997483891','망명북한작가 Pen 문학(2015년 제3호)','편집부','Pen International','92','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9791133415489','365 생일점(비밀이야!)','키누카','대원키즈','101','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9788978683715','정보시스템 전략과 계획','박용태','UUP','232','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788978682565','삶의 흔적과 여생의 길','안재화','UUP','208','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788978683791','영어와 국어 통사구조의 신탐구','장경기','UUP','500','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788917224665','함께하는 긍정','오연천','YBM','152','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788957367582','엄마와 보내는 마지막 시간 14일','리사 고이치','가나출판사','156','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788945220509','80일간의 세계 일주(세계명작 만화 컬렉션 5)','쥘 베른 (원작)','문공사','254','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9788932114309','민들레의 영토 (전2권)','이해인','가톨릭출판사','182','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9791129597267','당신의 자리. 1','최수현','가하','348','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9791129597274','당신의 자리. 2','최수현','가하','320','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9788975912757','BCM 부모 에센스. 1(BCM 부모교육 시리즈)','총회교육국','기독교대한성결교회','456','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788975912764','BCM 부모 에센스. 2(성결교회 부모교육 시리즈)','총회교육국','기독교대한성결교회','452','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788993635669','실컷 논 아이가 행복한 어른이 된다(행복한 성장 1)','김태형','갈매나무','468','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788958047025','피아니스트의 애인. 1','강한주','감','205','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9788958047032','피아니스트의 애인. 2','강한주','감','200','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9788958047049','피아니스트의 애인. 3','강한주','감','200','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994459592','눈금 없는 잣대(개미시선 22)','이훈식','개미','168','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994459608','새벽이 오는 소리(개미시선 23)','정상석','개미','352','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994459615','안에서 내다본 밖(개미시선 24)','최미림 외','개미','104','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994459622','푸른 하늘의 종이비행기(개미시선 25)','최부암','개미','104','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788996740025','천부경','김진일','거발환','320','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788952775498','에도가와 란포 결정판. 1','에도가와 란포','검은숲','479','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9788952775658','퀸 수사국(양장본 HardCover)','엘러리 퀸','검은숲','268','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9788986377507','자연과학사. 1: 고대 중세 근세 편','박인용','경당','284','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788986377514','자연과학사. 2: 근대 현대 편','박인용','경당','188','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788942008445','논리학 입문(14판)','Irving M. Copi','경문사','196','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788961059565','기하의 이해(GSP를 이용한)(3판)(생각하는 수학 11)','김향숙','경문사','272','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788961059572','도형의 이해(종이접기를 활용한)(생각하는 수학 9)','김향숙','경문사','288','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788961059589','구두 스토리텔링과 수학 교수법(경문수학교육학)','마이클 스테판 시로','경문사','84','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788983411105','미래를 여는 우리 근현대사','한영우','경세원','388','역사/문화');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125913375','Live 한국사. 1: 선사 시대와 고조선(양장본 HardCover)','윤상석','천재교육','100','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125913382','Live 한국사. 2: 고구려의 성장과 쇠퇴(양장본 HardCover)','이준범','천재교육','240','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125913399','Live 한국사. 3: 백제의 찬란한 문화(양장본 HardCover)','임창호','천재교육','356','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125913405','Live 한국사. 4: 신라의 발전(양장본 HardCover)','김미영','천재교육','304','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125913412','Live 한국사. 5: 통일신라 발해(양장본 HardCover)','박정영','천재교육','396','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9788938813985','Main Idea로 푸는 시편 1-75(Vol. 1)(메인 아이디어 시리즈 23)(양장본 HardCover)','스티븐 J. 로슨','디모데','268','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788938813992','Main Idea로 푸는 시편 76-150(Vol. 2)(메인 아이디어 시리즈 24)(양장본 HardCover)','스티븐 J. 로슨','디모데','304','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788949911786','황제와 자기(양장본 HardCover)','이희관','경인문화사','294','역사/문화');");
        db.execSQL("INSERT INTO book VALUES(null,'9788991226784','부가가치세 확정신고 납부실무(2015년도 제2기분)','편집부','경제법륜사','196','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788959964970','학교 문법의 이해. 1','나찬연','경진출판','184','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788959964987','학교 문법의 이해. 2','나찬연','경진출판','1288','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788928105151','OK속담(어휘력 100점)(손에 쏙 시리즈 2)','HR기획 (엮음)','효리원','344','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9788959965014','조선후기 통신사행록의 글쓰기 담론(양장본 HardCover)','정은영','경진출판','398','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965181699','뉴노멀 시대 어떻게 생존할 것인가?','대릴 콜린스','경향미디어','88','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965181705','회의하는 회사원(양장본 HardCover)','서대리','경향미디어','112','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965181682','나는 한류 장사꾼이다','황해진','경향미디어','194','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965541059','김동리 문학전집. 31: 문학이란 무엇인가(탄생 100주년 기념)(개정판)','김동리','계간문예','226','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965541363','세계인의 조건(계간문예수필선 101)','박지연','계간문예','12','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965541356','오솔길이 좋아(계간문예시인선 108)','엄기원','계간문예','256','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788975857294','동양사상과 현대적 가치(계명한국학총서 40)(양장본 HardCover)','계명대학교 한국학연구원','계명대학교출판부','156','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788975857287','셰익스피어 작품 각색과 다시쓰기의 정치성','김종환','계명대학교출판부','302','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788976418944','한국 상고문화와 중국 동북지방','박대재','고려대학교출판문화원','374','역사/문화');");
        db.execSQL("INSERT INTO book VALUES(null,'9788976418937','삼국유사(읽기 쉬운)','일연','고려대학교출판문화원','442','역사/문화');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994543765','사진하는 태도가 틀렸어요','박찬원','고려원북스','211','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788961431859','오라 그대여(들꽃동인선 44)','송수권','고흥작가회','238','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788963527857','교육심리학(2판)(양장본 HardCover)','문은식','공동체','705','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788963523088','평생교육개론','이향란','공동체','184','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788963527581','유아교육개론(아이미소)(개정증보판)(아이미소 유아교육과정 시리즈 1)','임미선','공동체','76','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788963528526','아동건강교육(2판)(양장본 HardCover)','주영은','공동체','200','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788963528571','영유아 놀이 지도(양장본 HardCover)','하정연','공동체','176','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788963528588','유아미술교육(자연친화적 접근의)(양장본 HardCover)','하정연','공동체','200','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788963522647','민감한 반응이 아이를 키운다','한동옥','공동체','192','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788964770801','지방세법해설(2016)(조문별(축조))(개정판 25판)(양장본 HardCover)','권강웅','광교이택스','748','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788964770795','이정국의 꿈을 향한 35년의 도전','이정국','광교이택스','1380','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125101420','초등수학의 진단과 처방(인식론적 장애 예방을 위한)','김용태','교우사','160','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125101437','곱셈 프랙탈 디자인을 만나다(융합교육 시리즈 10)','안대영','교우사','818','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125101444','나눗셈 프랙탈 디자인을 만나다(융합교육 시리즈 11)','안대영','교우사','940','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788930233545','Why? People 유일한','이재훈','예림당','794','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9788930200943','Why? 세계사: 영국(초등역사학습만화)(양장본 HardCover)','유기영','예림당','700','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9788967460754','Withim(위딤): Kids English Level. 2','빛의숲 편집부','빛의숲','740','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788967460761','Withim(위딤): Kids English Level.1','빛의숲 편집부','빛의숲','910','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965091455','가곡의 왕 슈베르트(지식똑똑 큰인물 탐구 36)(양장본 HardCover)','신세리','통큰세상','566','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125101451','덧셈 프랙탈 디자인을 만나다(융합교육 시리즈 8)','안대영','교우사','540','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9791125101475','뺄셈 프랙탈 디자인을 만나다(융합교육 시리즈 9)','안대영','교우사','304','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788925410173','정서중심치료','Leslie S. Greenberg','교육과학사','338','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788925410203','청소년이해론(제3판)(3판)','권이종','교육과학사','256','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788925410166','사회과교육론','김영석','교육과학사','368','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994591674','가자! 한성백제: 몽촌토성(발도장 쿵쿵)(개정판)(발도장 쿵쿵 역사시리즈 2)','황은희','핵교','164','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994591667','가자!! 대한민국: 4ㆍ19 기념관(발도장 쿵쿵)(개정판)(발도장 쿵쿵 역사시리즈 13)','양대승','핵교','304','아동');");
        db.execSQL("INSERT INTO book VALUES(null,'9788925410180','1756년의 북경이야기','이기경','교육과학사','308','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788925409887','아동권리 0-8','이재연','교육과학사','304','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788925410197','강의법에 의한 과학 교수-학습(Monograph Series 9)','최상희','교육과학사','428','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788925410142','질문이 이어지는 천문 실험 활동: 태양계를 중심으로','최승언','교육과학사','320','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788909193023','글쓰기 동기 전략(글쓰기 자신감을 길러 주는)(국어과 교수 학습 연구 총서 6)','Janet Angelillo','교학사','312','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788909193399','지도로 보는 세계사(프리뷰 히스토리 시리즈 1)','지도표현연구소','교학사','316','역사/문화');");
        db.execSQL("INSERT INTO book VALUES(null,'9788959667345','구개음화의 통시성과 역동성(국어학총서 26)(양장본 HardCover)','김주필','국어학회','432','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788957821107','주식 초보자가 가장 알고 싶은 101가지 이야기(전면개정판)(고수 따라하기 시리즈 6)','권정태','국일증권경제연구소','383','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788975995590','의사가 만난 퇴계(개정판)','김종성','궁미디어','428','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788976824240','마네의 회화(파레시아 총서 1)','마리본 세종 (엮음)','그린비(그린비라이프)','424','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994040813','캐롤','퍼트리샤 하이스미스','그책','300','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9788991358478','시를 그리는 사람','채린','글샘','230','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788996806776','감사기도일지','편집부','하늘정원(풍경문학회)','206','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788967352882','지구의 밥상','구정은','글항아리','240','역사/문화');");
        db.execSQL("INSERT INTO book VALUES(null,'9788967352950','0년(양장본 HardCover)','이안 부루마','글항아리','260','역사/문화');");
        db.execSQL("INSERT INTO book VALUES(null,'9788996587873','철학의 무대','미셸 푸코','기담문고','340','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788977237025','관광과 경영가치모델(양장본 HardCover)','박중환','기문사','735','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788966610914','산티아고 길의 마을과 성당','홍사영','기쁜소식','872','여행');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965238478','대한국인, 우리들의 이야기','박종인','기파랑','384','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788965238492','앙코르 와트의 신비','이태원','기파랑','432','여행');");
        db.execSQL("INSERT INTO book VALUES(null,'9788997766383','빨간 사과를 베끼다','김단혜','길동무','440','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788960525887','마리얼레트리. 2','오소리','길찾기','576','소설');");
        db.execSQL("INSERT INTO book VALUES(null,'9788934973249','김광석과 철학하기','김광식','김영사','512','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788934972495','지식','루이스 다트넬','김영사','168','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788934973089','리더 인 미','스티븐 코비','김영사','168','가정/생활');");
        db.execSQL("INSERT INTO book VALUES(null,'9788934973096','3개의 소원 100일의 기적(양장본 HardCover)','이시다 히사쓰구','김영사','168','자기계발');");
        db.execSQL("INSERT INTO book VALUES(null,'9788991567320','통합적 사티어 모델: 이론과 실제','김영애','김영애가족치료연구소','168','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788972916086','신의 사람들','그레이엄 핸콕','까치','168','역사/문화');");
        db.execSQL("INSERT INTO book VALUES(null,'9788954432146','운명 따위 이겨주마','오고다 마코토','꼼지락','168','자기계발');");
        db.execSQL("INSERT INTO book VALUES(null,'9788960924147','개혁주의 무천년설 옹호','샘 스톰스','부흥과개혁사','168','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788960923515','개혁주의 종말론 탐구(개혁주의 신학 시리즈 2)','코르넬리스 비네마','부흥과개혁사','168','종교');");
        db.execSQL("INSERT INTO book VALUES(null,'9788998400842','간호사(꿈결 잡 시리즈)','고정민','꿈결','416','자기계발');");
        db.execSQL("INSERT INTO book VALUES(null,'9788998400811','꿈과 끼를 찾는 자유학기제의 모든 것','양소영','꿈결','440','자기계발');");
        db.execSQL("INSERT INTO book VALUES(null,'9788930088473','시장의 철학(양장본 HardCover)','윤평중','나남','28','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788930088497','나는 평생 세금쟁이(나남신서 1849)','조용근','나남','28','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788968911118','회계원리 입문(2016)(스마트)','나눔 편집기획실','나눔','28','경제/경영');");
        db.execSQL("INSERT INTO book VALUES(null,'9788970342443','나는 한 번 읽은 책은 절대 잊어버리지 않는다','카바사와 시온','나라원','48','자기계발');");
        db.execSQL("INSERT INTO book VALUES(null,'9788998529109','과학기술과 사회(21세기 교양)','홍성욱','나무','32','과학');");
        db.execSQL("INSERT INTO book VALUES(null,'9788998850081','말하지 않아도','김사랑','나무늘보','174','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788992877350','말랄라 유사프자이(W 세상을 빛낸 위대한 여성 7)(양장본 HardCover)','윤해윤','나무처럼','552','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788997234684','하루 5분 기적의 성장 마사지(숨은 키 10cm 더 찾아주는)','송숙현','나비의활주로','684','건강');");
        db.execSQL("INSERT INTO book VALUES(null,'9788997234691','그토록 꿈꾸던 인생을 살아라','이대영','나비의활주로','276','자기계발');");
        db.execSQL("INSERT INTO book VALUES(null,'9788977460546','시로 읽는 주역','김재형','내일을여는책','392','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788994407395','경연 평화로운 나라로 가는 길(너머학교 고전교실 11)','오항녕','너머학교','160','인문');");
        db.execSQL("INSERT INTO book VALUES(null,'9788996335368','사랑은 하나입니다','박재견','네오이마주','136','시/에세이');");
        db.execSQL("INSERT INTO book VALUES(null,'9788963057187','10대를 위한 진로 특강(김상호의)','김상호','노란우산','269','자기계발');");


        //테스트DB
        db.execSQL("INSERT INTO book VALUES(null,'9788968483547','그림으로 개념을 이해하는 알고리즘','아디트야 바르가바','한빛미디어','310','자기계발');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(db);
    }

    //디비랑 리스트뷰랑 연결해주는것
    public ArrayList<BookInfo> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BookInfo> Book= new ArrayList<BookInfo>();
        Cursor result = db.rawQuery("select * from "+BOOK_TABLE_NAME , null);
        while(result.moveToNext()){
            boolean add = Book.add(new BookInfo(result.getString(result.getColumnIndex(BOOK_COLUMN_ID)),result.getString(result.getColumnIndex(BOOK_COLUMN_BOOKNAME)), result.getString(result.getColumnIndex(BOOK_COLUMN_WRITER)), result.getString(result.getColumnIndex(BOOK_COLUMN_PUBLISHER)), result.getString(result.getColumnIndex(BOOK_COLUMN_PAGE)),result.getString(result.getColumnIndex(BOOK_COLUMN_GENRE))));
        }
        return Book;
    }

    //일단 책이름과 작가만 넣어놓음
    public ArrayList<BookInfo> getselectData(String a){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BookInfo> Book= new ArrayList<BookInfo>();
        Cursor result = db.rawQuery("select * from book where (bookname like '%"+a+"%') or (writer like '%"+a+"%') or (publisher like '%"+a+"%') or (isbn like '%"+a+"%')" , null);
        while(result.moveToNext()){
            boolean add = Book.add(new BookInfo(result.getString(result.getColumnIndex(BOOK_COLUMN_ID)),result.getString(result.getColumnIndex(BOOK_COLUMN_BOOKNAME)), result.getString(result.getColumnIndex(BOOK_COLUMN_WRITER)), result.getString(result.getColumnIndex(BOOK_COLUMN_PUBLISHER)), result.getString(result.getColumnIndex(BOOK_COLUMN_PAGE)),result.getString(result.getColumnIndex(BOOK_COLUMN_GENRE))));
        }
        return Book;
    }


    //전체 통계
    public String total(String a){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from book where (bookname like '%"+a+"%') or (writer like '%"+a+"%') or (publisher like '%"+a+"%') or (isbn like '%"+a+"%')",null);
        String result = String.valueOf(c.getCount());

        return result;
    }





}
package mrzeinaliarya3624_mycar.mycarapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class dbHandler extends SQLiteOpenHelper {

    private static String DBNAME = "My_car.db";
    private static String DBPATH;
    private static String TBL_CAR="tbl_car";
    private static String TBL_STD="tbl_student";
    private static String TBL_CRS="tbl_course";
    private static String TBL_EDU="tbl_education";
    Context cntx;
    SQLiteDatabase db;

    public dbHandler(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        cntx = context;
        DBPATH = context.getCacheDir().getPath()+"/"+DBNAME;
    }

    public void open(){

        if(DbExist()){
            try{
                File f = new File(DBPATH);
                db = SQLiteDatabase.openDatabase(DBPATH,null,SQLiteDatabase.OPEN_READWRITE);

            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            if (CopyDb()){
                open();
            }
        }
    }

    public boolean DbExist(){
        File f = new File(DBPATH);
        if (f.exists()){
            return true;
        }else {
            return false;
        }
    }

    public boolean CopyDb(){

        try {

            FileOutputStream out = new FileOutputStream(DBPATH);
            InputStream in = cntx.getAssets().open(DBNAME);

            byte[] buffer = new byte[1024];
            int ch;

            while ((ch=in.read(buffer))>0){
                out.write(buffer,0,ch);
            }

            out.flush();
            out.close();
            in.close();

            return true;
        }catch (Exception e){

            return false;
        }
    }

    public String displayCar(){
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TBL_CAR ,null);
        cursor.moveToFirst();
        return cursor.getString(1);
    }
//,String chasi,String brnd ,String colr ,String plk ,String yer ,String ful ,String cvolum
        public void insertCar(String Typ ,String chasi,String brnd ,String colr ,String plk ,String yer ,String ful ,String cvolum ){
            ContentValues tp = new ContentValues();

            tp.put("typ",Typ);
            tp.put("chassis_typ",chasi);
            tp.put("brand",brnd);
            tp.put("color",colr);
            tp.put("plak",plk);
            tp.put("production_yer",yer);

            tp.put("fuel_type",ful);
            tp.put("cylinder_volume",cvolum);

            db.insert(TBL_CAR,"typ",tp);


        }
/*

        public List<Education> displayEducation(){
            Cursor cursor = db.rawQuery("SELECT * FROM  tbl_education ",null);
            cursor.moveToFirst();
            List<Education> EduList = new ArrayList<>();
            while (cursor.moveToNext()){
                Education edu = new Education();
                edu.setId_edu(cursor.getString(0));
                edu.setName_edu(cursor.getString(1));

                EduList.add(edu);
            }
            return EduList;
        }

        public List<Student> displayStudent(){
            Cursor cursor = db.rawQuery("SELECT * FROM  "+ TBL_STD,null);
            cursor.moveToFirst();
            List<Student> StdList = new ArrayList<>();
            do{
                Student std = new Student();
                std.setId(cursor.getString(0));
                std.setName(cursor.getString(1));
                std.setImg(cursor.getBlob(2));

                StdList.add(std);
            }while (cursor.moveToNext());
            return StdList;
        }


        public Student displayStudent(String id){


            Cursor cursor = db.rawQuery("SELECT id_student , name_student , pic , name_education FROM  "+ TBL_STD+" INNER JOIN "+ TBL_EDU +" on education = id_education where id_student="+id,null);
            cursor.moveToFirst();

                Student std = new Student();
                std.setId(cursor.getString(0));
                std.setName(cursor.getString(1));
                std.setImg(cursor.getBlob(2));
                std.setEducation(cursor.getString(3));

            return std;
        }

        public List<Course> displayCourse(){
            Cursor cursor = db.rawQuery("SELECT * FROM  "+TBL_CRS,null);
            cursor.moveToFirst();
            List<Course> CRSList = new ArrayList<>();
            do {
                Course crs = new Course();
                crs.setId(cursor.getString(0));
                crs.setCourseName(cursor.getString(1));
                crs.setCourseTuition(cursor.getString(2));

                CRSList.add(crs);
            }while (cursor.moveToNext());
            return CRSList;
        }

        public List<Education> displayEducation(String a){
            Cursor cursor = db.rawQuery("SELECT * FROM  tbl_education where id_education<>1 and name_education like '"+a+"%'",null);
            cursor.moveToFirst();
            List<Education> EduList = new ArrayList<>();
            do {
                Education edu = new Education();
                edu.setId_edu(cursor.getString(0));
                edu.setName_edu(cursor.getString(1));

                EduList.add(edu);
            }while (cursor.moveToNext());
            return EduList;
        }




        public int DisplayEduCount(String a){
            Cursor cursor = db.rawQuery("SELECT * FROM  tbl_education where  id_education<>1 and  name_education like '"+a+"%'",null);
            cursor.moveToFirst();
            return cursor.getCount();
        }


        public int DisplaySTDCount(){
            Cursor cursor = db.rawQuery("SELECT * FROM "+TBL_STD,null);
            cursor.moveToFirst();
            return cursor.getCount();
        }

        public void insertEducation(String a){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name_education",a);

            db.insert("tbl_education","name_education",contentValues);
        }

        public void insertCrs(String Name , String Tuition){
            ContentValues contentValues = new ContentValues();
            contentValues.put("nameCrs",Name);
            contentValues.put("crsFee",Tuition);

            db.insert(TBL_CRS,"nameCrs",contentValues);
        }

           public List<Unit> displayUnits(String str){
            Cursor cursor = db.rawQuery("SELECT * FROM  "+TBL_CAR+" where UnitName like '%"+str+"%'",null);
            cursor.moveToFirst();
            List<Unit> UnitList = new ArrayList<>();
            do{
                Unit unit = new Unit();
                unit.setId(cursor.getString(0));
                unit.setUnitName(cursor.getString(1));

                UnitList.add(unit);
            }while (cursor.moveToNext());
            return UnitList;
        }

    public int displayEducationCount(){
        Cursor cursor = db.rawQuery("SELECT * FROM  tbl_education ",null);
        cursor.moveToFirst();

        return cursor.getCount();
    }


    public int displayCourseCount(){
        Cursor cursor = db.rawQuery("SELECT * FROM  "+ TBL_CRS,null);
        cursor.moveToFirst();

        return cursor.getCount();
    }


    @Override
    public synchronized void close() {
        super.close();
    }


*/
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

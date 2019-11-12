package com.fkq.skill.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * 文件管理工具
 */

public class FileUtil {

    private static FileUtil fileUtil;
    private static Context mContext;

    public static FileUtil getIntance(Context context) {
        mContext = context;
        if (fileUtil == null) {
            fileUtil = new FileUtil();
        }
        return fileUtil;
    }

    /**
     * 获取包名目录下cache目录
     *
     * @return Android/data/你的应用包名/cache/目录
     */
    public String getCachePath() {
        return mContext.getExternalCacheDir().getAbsolutePath();
    }


    /**
     * 获取包名下web目录下指定
     *
     * @return Android/data/你的应用的包名/files/目录
     */
    public String getAppointPath(String path) {
        return mContext.getExternalFilesDir(path).getAbsolutePath();
    }

    /**
     * 获取当前程序缓存路径(root后可见)
     * 返回通过Context.openFileOutput()创建和存储的文件系统的绝对路径，应用程序文件，这些文件会在程序被卸载的时候全部删掉。
     *
     * @return /data/data/com.hylink.fire/files
     */
    public String getFilesDirPath() {
        return mContext.getFilesDir().getAbsolutePath();
    }

    /**
     * 获取当前程序缓存路径(root后可见)
     * 返回通过Context.openFileOutput()创建和存储的文件系统的绝对路径，应用程序文件，这些文件会在程序被卸载的时候全部删掉。
     *
     * @return /data/data/com.hylink.fire/cache
     */
    public String getCacheDirPath() {
        return mContext.getCacheDir().getAbsolutePath();
    }

    /**
     * 获取该程序的安装包路径(root后可见)
     * 返回android 安装包的完整路径，这个包是一个ZIP的要锁文件，它包括应用程序的私有资源。
     *
     * @return /data/app/com.hylink.fire/base.apk
     */
    public String getPackageResourcePath() {
        return mContext.getPackageResourcePath();
    }

    /**
     * 获取程序默认数据库路径(root后可见)
     *
     * @return /data/data/com.hylink.fire/databases/dbName
     */
    public String getDatabasePath(String dbName) {
        return mContext.getDatabasePath(dbName).getAbsolutePath();
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param filePath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public boolean deleteFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {
                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除文件夹以及目录下的文件
     *
     * @param filePath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String filePath) {
        boolean flag;
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        File[] files = dirFile.listFiles();
        //遍历删除文件夹下的所有文件(包括子目录)
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                //删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {
                //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前空目录
        return dirFile.delete();
    }

    public long getFileSize(String filePath) {
        long size = 0;
        File file = new File(filePath);
        FileInputStream fis = null;
        try {
            if (file.exists()) {
                fis = new FileInputStream(file);
                size = fis.available();
            } else {
                file.createNewFile();
                Log.e("获取文件大小", "文件不存在!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * base64解码成File文件
     */
    public void base64ToFile(String filePath, String base64) {
        File file = new File(filePath);
        FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
            fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String[] units = {"B", "KB", "MB", "GB", "TB"};

    private String getUnit(float size) {
        int index = 0;
        while (size > 1024 && index < 4) {
            size = size / 1024;
            index++;
        }
        return String.format(Locale.getDefault(), " %.2f %s", size, units[index]);
    }

    /**
     * 根据文件路径转换为Base64格式返回
     *
     * @param path   文件路径
     * @param offset 文件读取起始位置
     * @param length 文件读取结束位置
     * @return
     */
    public String encodeBase64File(String path, long offset, long length) {
        String base64 = null;
        RandomAccessFile raf;
        try {
            File file = new File(path);
            raf = new RandomAccessFile(file, "r");
            raf.seek(offset);
            byte[] bytes = new byte[(int) length];
            raf.read(bytes, 0, (int) length);
            //关闭输入流
            raf.close();
            base64 = Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }

    /**
     * 保存本地图片
     *
     * @param filePath
     * @param bytes
     */
    public void saveImageFromBase64(String filePath, byte[] bytes) {
        //创建文件，因为不存在2级目录，所以不用判断exist，要保存png，这里后缀就是png，要保存jpg，后缀就用jpg
        File file = new File(filePath);
        try {
            //文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            //压缩图片，如果要保存png，就用Bitmap.CompressFormat.PNG，要保存jpg就用Bitmap.CompressFormat.JPEG,质量是100%，表示不压缩
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Bitmap outB = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(outB);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap, 0, 0, null);
            outB.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
            //写入，这里会卡顿，因为图片较大
            fileOutputStream.flush();
            //记得要关闭写入流
            fileOutputStream.close();
            //成功的提示，写入成功后，请在对应目录中找保存的图片
            Log.d("TAG", "写入成功！目录" + filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //失败的提示
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            //失败的提示
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 进行质量压缩
     * 测试  测测看看保存的照片是什么样子
     *
     * @param image
     * @param limit 压缩到多少kb以内
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream compressImageOutputStream(Bitmap image, String outPath, int limit) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > limit) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 5;// 每次都减少5
        }
        FileOutputStream fos;
        try {
            System.out.println("保存文件位置：" + outPath);
            fos = new FileOutputStream(outPath);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            System.out.println("保存出错：" + outPath);
        }
        return baos;
    }


    public void saveImage(File file, Bitmap bitmap) {
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

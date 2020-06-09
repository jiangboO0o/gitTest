package com.jiangbo.gitTest.util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import sun.reflect.generics.repository.FieldRepository;

import java.io.File;

/**
 * @program: jgitTest
 * @description: jgit测试
 * @author: LongJiangBo
 * @create: 2020-05-27 15:38
 **/
public class GitTest {
    //定义本地git路径
    public static final String LOCALPATH = "F:\\java\\github.com\\jiangboO0o\\stock";
    //.git文件路径
    public static String LOCALGITFILE = LOCALPATH + ".git";
    //远程仓库地址
    public static final String REMOTEREPOURI = "https://gitlab.com/jiangboO0o/stock.git";
    //操作git的用户名
    public static final String USER = "jiangboO0o";
    //密码
    public static final String PASSWORD = "ljb121002";
    //git远程仓库服务器ip
    public static final String HOST = "https://gitlab.com/jiangboO0o";
    //建立与远程仓库的联系，仅需要执行一次
    public static String setupRepo(){
        String msg = "";
        try {
            Git git = Git.cloneRepository()
                    .setURI(REMOTEREPOURI)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(USER,PASSWORD))
                    .setBranch("master")
                    .setDirectory(new File(LOCALPATH))
                    .call();
            msg = "git init success!";
        }catch (Exception e){
            msg = "git已经初始化！";
        }
        return msg;
    }

    public static boolean pullBranchToLocal(){
        boolean resultFlag = false;
        //git仓库地址
        Git git;
//        git = new Git(new FieldRepository(LOCALGITFILE));
return resultFlag;
    }
}

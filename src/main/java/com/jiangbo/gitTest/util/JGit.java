package com.jiangbo.gitTest.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

/**
 * @program: gitTest
 * @description:
 * @author: LongJiangBo
 * @create: 2020-05-28 15:27
 **/
@Slf4j
public class JGit {

    //远程库路径
    public String remotePath = "https://gitlab.com/jiangboO0o/shop.git";
    //下载已有仓库到本地路径
    public static String localPath = "D:\\JavaWorkSpace\\shop\\";
    //账户名
    public static String username = "";
    //密码
    public static String password = "";
    //分支
    public static String branch = "";

    /**
     * @Description: 仓库初始化
     * @Author: LongJiangBo
     * @param
     * @return: void
     * @Date: 16:28 2020/5/28
    */
    public static void gitInit() throws IOException {
        Repository newCreatedRepo = FileRepositoryBuilder.create(new File(localPath + "/.git"));
        if (newCreatedRepo != null){
            newCreatedRepo.create();
        }
    }

    /**
     * @Description: 添加到缓存区：对于删除的文件jgit添加失败
     * @Author: LongJiangBo
     * @param
     * @return: void
     * @Date: 17:39 2020/5/28
    */
    public static void gitAdd() throws GitAPIException, IOException {
        Git git = new Git(new FileRepository(localPath + "/.git"));
        git.add().addFilepattern(".").call();
        log.info("git add success");
    }

    /**
     * @Description: 本地代码提交
     * @Author: LongJiangBo
     * @param msg
     * @return: void
     * @Date: 17:42 2020/5/28
    */
    public static void gitCommit(String msg) throws IOException, GitAPIException {
        Git git = new Git(new FileRepository(localPath + "/.git"));
        //全部提交
        git.commit().setAll(true).setMessage(msg).call();
        log.info("git commit success");
    }

    /**
     * @Description: push本地代码到远程仓库
     * @Author: LongJiangBo
     * @param branch
     * @param userName
     * @param passsWord
     * @return: void
     * @Date: 10:12 2020/5/29
    */
    public static void gitPush(String branch,String userName,String passsWord) throws IOException, GitAPIException {
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(userName,passsWord);
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));
        git.push().setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }

    public static void gitPull(String branch,String userName,String passsWord) throws IOException, GitAPIException {
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(userName,passsWord);
        Git git = new Git(new FileRepository(localPath+"/.git"));
        git.pull().setRemote("origin").setRemoteBranchName(branch).setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }

    public static void main(String[] args) {
        try {
            gitPull(branch,username,password);
            gitCommit("测试");
            gitPush(branch,username,password);
        }catch (IOException| GitAPIException e){
            e.printStackTrace();
            System.out.println("失败");
            return;
        }
        System.out.println("成功");
    }
}

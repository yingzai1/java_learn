# 1、常用指令
## 1.1 本地仓库操作
git log
git status
git reset 版本号       将暂存区的文件取消暂存或者是切换到指定版本
git commit -m "输入提交信息"
git add 文件名
## 1.2 远程仓库操作
git remote     查看远程仓库
git remote add origin https://gitee.com/yingzi-6/java147_demo.git 
git clone https://gitee.com/yingzi-6/java147_demo.git
==git pull origin master==
注意：如果当前本地仓库不是远程仓库克隆，而是本地创建的仓库，并且仓库中存在文件，此时再从远程仓库拉去文件的时候就会报错（==fatal:refusing to merge unrelated histories==），解决此问题可以在git pull命令后面加入参数 
git pull origin master==--allow--unrelated-histories==
==git pull --allow--unrelated-histories origin master==
==git push origin master==
## 1.3 git的分支操作
### 分支操作
git branch
git branch -r
git branch -a
### 创建分支
git branch [name]
### 切换分支
git checkout [name]
### 推送至远程仓库分支
git push [shortname][name]
>其中[shortname] 一般填“origin”
### 合并分支 
git merge [name]
eg:将b1分支合并到master上去
步骤（先切在合并）：
- step1：切换至master分支
- step2：执行git merge b1
### 标签操作
git tag -列出已有的标签
git tag [name] -创建一个标签
git push [shortname][name]
git checkout -b [branch][name]-将标签对应的代码复制到一个新的branch中。
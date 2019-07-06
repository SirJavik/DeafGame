package net.javik.DeafGame.DeafUpdater;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class DeafUpdater {
    private Repository DeafRepo;
    private Git DeafGit;

    private String branch;

    public DeafUpdater(String branch) {
        try {
             this.DeafRepo = new FileRepositoryBuilder()
                     .setGitDir(new File(System.getProperty("user.dir") + "/.git" ))
                    .build();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        this.branch = branch;

        this.DeafGit = new Git(this.DeafRepo);
    }

    public boolean updateCheck() {
        PullCommand pullCmd = this.DeafGit.pull();
        pullCmd.setRemoteBranchName(this.branch);


        try {
            PullResult pullResult = pullCmd.call();
            String pullState = pullResult.getMergeResult().toString();

            if(pullState.lastIndexOf("Already-up-to-date") > 0) {
                return false;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return true;
    }
}

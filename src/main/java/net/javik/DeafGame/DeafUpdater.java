package net.javik.DeafGame;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class DeafUpdater {
    private Repository DeafRepo;
    private Git DeafGit;

    public DeafUpdater() {
        try {
             this.DeafRepo = new FileRepositoryBuilder()
                     .setGitDir(new File(System.getProperty("user.dir")))
                    .build();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        this.DeafGit = new Git(this.DeafRepo);
    }

    public boolean updateCheck() {
        PullCommand pullCmd = this.DeafGit.pull();

        try {
            pullCmd.call();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}

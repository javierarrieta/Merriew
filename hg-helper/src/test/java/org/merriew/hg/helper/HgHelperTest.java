package org.merriew.hg.helper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class HgHelperTest {

	private static final String REPO1 = "src/test/repos/simpleRepo_1";
	private static final String BLANK_REPO = "target/repos/blank";
	private HgHelper helper = new HgHelper();

	public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	@Before
	public void init() throws IOException, InterruptedException {
		
		Logger.getLogger(HgHelper.class.getPackage().getName() ).setLevel(Level.FINEST);
		
		File repo = new File(BLANK_REPO);

		if (repo.exists() && repo.isDirectory() ) {
			// delete repo
			deleteDirectory(repo);
		}
		helper.init(BLANK_REPO);
	}

	@Test
	public void testPull() throws IOException, InterruptedException {
		String repo = REPO1;
		helper.pull(repo, new File(BLANK_REPO),true);

		File f = new File(BLANK_REPO, "test.txt");

		Assert.assertTrue("Repo should have pulleda a file 'test.txt'",
				f.exists());

	}
}

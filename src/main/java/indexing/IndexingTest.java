package indexing;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * Chapter 2.3.1 Listing 2.1 <br>
 * Adding documents to an index
 * @author Zhang Gang
 * @date 2014年10月31日
 */
public class IndexingTest {
	protected String[] ids = {"1", "2"};
	protected String[] unindexed = {"Netherlands", "Italy"};
	protected String[] unstored = {"Amsterdam has lots of bridges", "Venice has lots of canals"};
	protected String[] text = {"Amsterdam", "Venice"};
	
	private Directory directory;
	
	protected void setUp() throws Exception{
		directory = new RAMDirectory();
		
		IndexWriter writer = getWriter();
	}

	private IndexWriter getWriter() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

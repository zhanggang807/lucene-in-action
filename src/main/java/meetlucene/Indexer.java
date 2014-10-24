package meetlucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * chapter 1.4
 * @author Zhang Gang
 * @date 2014年10月23日
 */
public class Indexer {

	public static void main(String[] args) throws Exception{
		if (args.length != 2){
			throw new IllegalArgumentException("Usage: java " + Indexer.class.getName() 
					+ " <index dir> <data dir>");
		}
		String indexDir = args[0];
		String dataDir = args[1];
		long start = System.currentTimeMillis();
		Indexer indexer = new Indexer(indexDir);
	}
	
	private IndexWriter writer;
	
	public Indexer(String indexDir) throws IOException{
		Directory dir = FSDirectory.open(new File(indexDir));
		writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), true, 
				IndexWriter.MaxFieldLength.UNLIMITED);
	}
	
	public void close() throws IOException{
		writer.close();
	}

}

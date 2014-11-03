package meetlucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Chapter 1 Fragments
 * @author Zhang Gang
 * @date 2014年11月3日
 */
public class Fragments {
	
	public void simpleSearch() throws IOException{
		Directory dir = FSDirectory.open(new File("/tmp/index"));
		IndexSearcher searcher = new IndexSearcher(dir);
		Query q = new TermQuery(new Term("contents", "lucene"));
		TopDocs hits = searcher.search(q, 10);
		System.out.println(hits);
		searcher.close();
	}

}

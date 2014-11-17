package searching;

import junit.framework.TestCase;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import common.TestUtil;

/***
 * Chapter 3.1.1 Listing 3.1<br>
 * Simple searching with TermQuery
 * @author Zhang Gang
 * @date 2014年11月13日
 */
public class BasicSearchingTest extends TestCase{
	
	public void testTerm() throws Exception {
		Directory dir = TestUtil.getBookIndexDirectory();
		IndexSearcher searcher = new IndexSearcher(dir);
		
		Term t = new Term("subject", "ant");
		Query query = new TermQuery(t);
		TopDocs docs = searcher.search(query, 10);
		assertEquals("Ant in Action", 1, docs.totalHits);
		
		t = new Term("subject", "junit");
		docs = searcher.search(new TermQuery(t), 10);
		assertEquals("Ant in Action, " + "JUnit in Action, Second Edition", 2, docs.totalHits);
		searcher.close();
		dir.close();
	}
			
}

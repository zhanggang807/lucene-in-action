package meetlucene;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

public class UpdateTest {
	
	protected String[] ids = {"1", "2"};
	protected String[] unindexed = {"Netherlands", "Italy"};
	protected String[] unstored = {"Amsterdam has lots of bridges", "Venice has lots of canals"};
	protected String[] text = {"Amsterdam", "Venice"};

	@Test
	public void test() throws Exception{
		Directory directory = new RAMDirectory();
		IndexWriter writer = new IndexWriter(directory, new WhitespaceAnalyzer(), IndexWriter.MaxFieldLength.UNLIMITED);
		for (int i = 0; i < 2; i++){
			Document doc = new Document();
			doc.add(new Field("id", ids[i], Field.Store.YES, Field.Index.NOT_ANALYZED));
			doc.add(new Field("text", text[i], Field.Store.YES, Field.Index.NOT_ANALYZED));
			writer.addDocument(doc);
		}
		System.out.println("共写入" + writer.numDocs() + "个文档");
		writer.close();
		printDoc(directory); 
		System.out.println("1111");
		Document docTest = new Document();
		docTest.add(new Field("id", "1", Field.Store.YES, Field.Index.NOT_ANALYZED));
		docTest.add(new Field("text", "new Text", Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		System.out.println("1111.5");
		try {
			writer.updateDocument(new Term("id", "1"), docTest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1111.6");
		writer.close();
		System.out.println("1111.7");
		System.out.println("2222");
		printDoc(directory); 
		System.out.println("3333");
	}
	
	public void printDoc (Directory directory) throws Exception{
		System.out.println("开始打印");
		IndexReader reader = IndexReader.open(directory);
		int count = reader.numDocs();
		for(int i = 0; i < count; i++){
			Document doc = reader.document(i);
			System.out.println(doc.get("id"));
			System.out.println(doc.get("text"));
		}
	}

}

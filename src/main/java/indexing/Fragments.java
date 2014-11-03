package indexing;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
/**
 * Chapter 2 Fragments
 * @author Zhang Gang
 * @date 2014年11月3日
 */
public class Fragments {
	
	public static final String COMPANY_DOMAIN = "example.com";
	public static final String BAD_DOMAIN = "yucky-domain.com";

	/**Chapter 2.5.1 Listing 2.4<br> 
	 * Selectively boostring documents and fields*/
	public void docBoostMethod() throws IOException {
		Directory dir = new RAMDirectory();
		IndexWriter writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), IndexWriter.MaxFieldLength.UNLIMITED);
		
		//START
		Document doc = new Document();
		String senderEmail = "bob@smith.com";
		String senderName = "Bob Smith";
		String subject = "Hi there Lisa";
		String body = "I don't have much to say";
		doc.add(new Field("senderEmail", senderEmail, Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("senderName", senderName, Field.Store.YES, Field.Index.ANALYZED));
		doc.add(new Field("subject", subject, Field.Store.YES, Field.Index.ANALYZED));
		doc.add(new Field("body", body, Field.Store.NO, Field.Index.ANALYZED));
		String lowerDomain = COMPANY_DOMAIN.toLowerCase();
		if (isImportant(lowerDomain)){
			doc.setBoost(1.5F);
		} else if (isUnimportant(lowerDomain)){
			doc.setBoost(0.1F);
		}
		writer.addDocument(doc);
		//END
		writer.close();
	}
	
	private boolean isImportant(String lowerDomain) {
	    return lowerDomain.endsWith(COMPANY_DOMAIN);
	}

	private boolean isUnimportant(String lowerDomain) {
	    return lowerDomain.endsWith(BAD_DOMAIN);
	}
}


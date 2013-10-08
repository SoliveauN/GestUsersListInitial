package fr.renater.idegest.tu;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class GestAjoutUsersTest extends TestCase {

	private GestAjoutUsers gau;

	@Before
	public void setUp() throws Exception {
		try {
			this.gau = new GestAjoutUsers("testusers.xml");
		} catch (IOException e) {
			fail("Création de l'OUT impossible !");
		}
	}

	@Test
	public void testGenUid() {
		// String s = null;
		// int len = s.length();
		// fail("Not yet implemented");
	}

	@Test
	public void test2PremiersCarsGenUid() {
		String uid = this.gau.genUid("Bob", "Martin");
		assertTrue("Les 2 premiers caractères sont valides",
				uid.startsWith("bm"));
	}

	@Test
	public void test2PremiersCarsGenUidBis() {
		String uid = this.gau.genUid("Bob", "Martin");
		String premscar = uid.substring(0, 2);
		assertEquals("Les 2 premiers caractères sont valides", "bm", premscar);
	}

	@Test
	public void testUidEnMinuscule() {
		String uid = this.gau.genUid("Bob", "Martin");
		assertEquals("Le uid est en minuscule", uid.toLowerCase(), uid);
	}

	@Test
	public void testUidLongeur() {
		String uid = this.gau.genUid("Bob", "Martin");
		assertTrue("Le uid est compris entre 5 et 9 carctères",
				uid.length() >= 5 && uid.length() <= 9);
	}
	
	@Test
	public void testCaracSpeciaux() {
		String uid = this.gau.genUid("Bob", "Martin");
		String CaracSpec = "([a-z]*)";
		assertTrue("Aucun caractère spécial n'est présent", uid.matches(CaracSpec));
	}

	@Test
	public void testUidIdentique() {
		String uid = this.gau.genUid("Bob", "Martin");
		String uid2 = this.gau.genUid("Nicolas", "Soliveau");
		assertFalse("Le uid est deja dans le fichier XMl", uid.equals(uid2));
		}
	
	@Test
	public void testGenPassword() {
		String uidPwd = this.gau.genPassword(8);
		assertTrue("Le mot de passe contient 8 caractères", uidPwd.length() == 8);
	}
	
	@Test
	public void testGenPwdDiff() {
		String uidPwd = this.gau.genPassword(8);
		String uidPwd2 = this.gau.genPassword(8);
		String uidPwd3 = this.gau.genPassword(8);
		String uidPwd4 = this.gau.genPassword(8);
		System.out.println("Pass1 : "+uidPwd+"\nPass2 : "+uidPwd2+"\nPass3 : "+uidPwd3+"\nPass4 : "+uidPwd4);
		assertTrue("Les mots de passe générés sont tous différents", (!uidPwd.equals(uidPwd2) && !uidPwd.equals(uidPwd3) && !uidPwd.equals(uidPwd4) && !uidPwd3.equals(uidPwd2) && !uidPwd4.equals(uidPwd2) && !uidPwd4.equals(uidPwd3)));
	}
}
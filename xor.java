import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class xor {
	public static void main(String[] args) throws IOException {
		xor binary = new xor();
		
		/* Read two files into 8 bits: -128..127 */
		byte[] bytes1 = binary.readBinaryFile("./test1.jpg");
		byte[] bytes2 = binary.readBinaryFile("./test3.jpg");
		
		/* Print size of each file */
		System.out.println("Size 1: " + bytes1.length);
		System.out.println("Size 2: " + bytes2.length);
		
		/* Convert to binary, xor, return byte[] array */
		byte[] bytes3 = xor(bytes1, bytes2);
		System.out.println("Size 3: " + bytes3.length);
		
		binary.writeBinaryFile(bytes3, "./test4.jpg");
	}

	byte[] readBinaryFile(String filename) throws IOException {
		Path path = Paths.get(filename);
		return Files.readAllBytes(path);
	}

	void writeBinaryFile(byte[] arrBytes, String filename) throws IOException {
		Path path = Paths.get(filename);
		Files.write(path, arrBytes);
	}

	static byte[] xor(byte[] b1, byte[] b2) {
		byte[] b3;
		int bigSize = 0, smallSize = 0, i=0;
		if(b1.length >= b2.length){
			b3 = new byte[b1.length];
			bigSize = b1.length;
			smallSize = b2.length;
		}
		else{
			b3 = new byte[b2.length];
			bigSize = b2.length;
			smallSize = b1.length;
		}
		
		for (i = 0; i < smallSize; i++) {
			b3[i] = (byte)(b1[i] ^ b2[i]);
			//System.out.print(i + " ");
		}
		for( ; i<(bigSize - smallSize); i++){
			if(b1.length >= b2.length){
				b3[i] = b1[i];
			}else{
				b3[i] = b2[i];
			}
		}
		return b3;
	}
}

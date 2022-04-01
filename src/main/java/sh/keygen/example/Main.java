package sh.keygen.example;

import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.encoders.Hex;
import org.json.JSONObject;
import org.json.JSONException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.Base64;

public class Main {
  public static void main(String args[]) {
    String licenseFile = "-----BEGIN LICENSE FILE-----\neyJlbmMiOiJRbmVpZXZvMDl4clZWcXdMYm1MVCt0RU5wa1A5Z0ZqMlg2cmJZ\nNDFNZ0UzamlQZGVRRE9SZS8wZU5CbU9ZbnRvU3JHQzhUa1dYVzRQQWFoREdT\ncUR0WHhuQ3RTY09hRlJtMzI0M2w1TU8wNDlpaVE3RlZuaHlyeGNrb2loNUUx\neUdOSlMvN1VRcFhFTUNuaWwxTFVTL1BMWVk4dGlKQmV6bThqQlVGUVN4enRW\nVWUrMFVGelZLSW5VZ0VYY1dSQk1INHVlU3g4L1NibWkxY2twSWNES2hIajdo\nVnlrT0xFanNmMXpRb1FOY0pSejZHM0J2SHJmcHpJazZ0blppaVdGY3c4akp2\nR2pGNWpjOEl3ZGpxQjhaLzJmOGlHTi9YQm14RlNKWWZvOHJxSUZHVDZPbFZl\nOUpJMGRRZjVCTE1neStBWXU5K05nbE5mMlpLMFNkZk93aDFnN1ArNDEyRTBn\ncXJqcWUxUXNOOWRGZDFPcytuRjR1MS9sVklwMFp0RVdoUlQvc0U3MldRcGQx\neHdqenB5dEtNT2V0bG1reDVKVE03TldOVk15d1FoanRVbm5Zc0wwNTRLaURU\nTlZ5dmQ4RHBjTmViT2tpR0xzNVhUa1B2TWJWSkZGRlEvdk5Mam5qZVRucVF3\nS1lhNTkrdVdhZnlIYnVuUUt1cmNSRHJQQUZaZjFmSThzWHBXd2lGY0lQY3JJ\nZ3NwY0lQZVFuYTJJWUx0ZExOZnhrYlNzMmRVemphMWFvVGRPWVBYbVJDUVUv\nZGhqb0tCZTRzZERiL1VqR1FBZnFFVzB1V1pqbTNmR0c5SmY3RExROEZ2cTVw\nNnNLNCtpNjFSR3NVeXdxVFgydkdxM0ZyZmpGRlhuNTViUDJNQ2YvV1Awc3RO\nbFpOYWhPR2xFam9WY3dyeGFkOERBY2E4VGtqdXZ0RHdnakhvL2NmZnpsM295\nVjFWMHAvcVFGMTlOL0Ztc0Q3dXBuVWRjM1JMTmZFYkU1bmZLb0pQZTExVU82\nRHZCdThXTFhKS1U0Z2p0dTNFTVViaXpXTThYR1pjeTlOZWsxUVF2dFc3a294\nYXo4aTNBSEx0YWlnS2tQREVPdTJzbTJyN1dvWmpVcWhkUFpZdUNQN2UrZVh5\nb3NaSy9nbWZSZXU1WkJnV0dpcnM1eTdHRStSYnZlOWpSb2dnekEvSHc2aHA4\nQ1JWUG4vS2ZxUkdJOUdxd2gxbll2SXl2bE5OZXpHMzBqSTM0S1hYRU9DQTFs\nU3pxckhSSFZFc2VGWWlwZjd5TjZ3SjJoYk5zUkhmd3h2ZUFLSWZHUmo0Q0Ja\nNlNwVWcvVndQT2swblc5Y3grWWR2TkxUa1FjWm1Yc0xmYjNZUkszNlcyYUJB\nUXFCL3lMbXNRMjRvdHdjSlhLR2wwNzFoa0tVdXdzb0ZyUTdtZVU1dTNKQkpG\nVjdGeVJHSjNFcTZHdzZ1TFNGZDVhbGV6eGZXVmd6TXpsYzhQL1hjb1piZ3Bi\nZDJGRWhpQ0lqblQ1RzAwZEZENURsM0h1bjY0RFZpVGhmbFZmOHY4cURqakVE\naG9ER2ZSRlVqWEd6L3dDbGN2YjFZUnlHUW9iTGhqblcrek96Mlp6aTRtR1V3\nK0t0Y3dnOUNlZ1BRelI4MVp2V1g0WEc0UFBTUzV2V3g2aWVHMWpaTUZzRHBZ\ncUJrazR1NG85YkR3WFdLbFNkeVFYUlJKU2NtRmdUdnF2UUtmMjVHKy9DZFZY\nV2tQbmNMU0s4WTgrOHY2bHkwYW50WjJkQVVDRE9FWHAzL3hNOVlQZnlXb2Q5\neUhoRStuaUl5WDJ1S0hkZldLWE5uUDdiVFZRblczYVdUeGhRMmt0VjloS1F6\nTzdqOTRzamxoYnZMN2JTZU10OUZOVDRLKzd3VTdKaGNEU1oxV0Z3SEx6b0NO\nV2JiMjUrUGd4RWRRR0N1Wk5hZTZZbHlYMlY4QnhLdjA4NzRZNmx2Z0YxdVBG\nbnk0RXpSTUZBUGhiM2d3cG03dytLVlVOVVpmV1ZUcUltaHRPZFUxSzBpczNL\nbi9YRkoxQ2FRVnZxalVhVFhSTzY0aEFYMzlUWlRqam43REpFTXBZWk8wSHIv\nZENzZkZyRDVRanZSU1FTNTh4OWgxWFNua05GRzVTM1prNmFUVTlSOWVmU1h1\nVVVJblFqcDJCVjYzeHJ3STZWR1pHMlBFR3BoWTZZTVEyOFNtcVNMZ2JqUkVL\naWtFTXZUS0EwT2Vva3g2U1VvR0FWbUpEbXJZSHVIMjNTYUFweEt1YVNzUVMw\nSU4vVlFEeU1qNlZsMENZZE44K2lwd1lHZElYUFpCZkpzNlBDTnBkczVYTzI1\nSHFWZUVXRk5acitGNElTMmlRWGlEbkV6anozUk4zSWpGT2IzdnlzbkN6Zito\nVnRKWVNsOTRlYWhuOXhnOEN5TWV2c0IxREpTWFJPeGFwSVRqem9DTDloZXRU\nd1Z3Q0xCS2FEZWZqQUVmRUZYMm5raW5oSEpTeE1EWkM1Z0dBMThCYUdkQlJQ\nYUNNbzhhYWJlRFNXUXJIMFpjbjUwUlVCTVUwaTF5QlNnNGd0djZCTG5NdDQy\nOGxPSXJ4YUR6bm43VTEwbkFVOHlCN1YzQzV4aGxOL1d1OWpnMVhzcW9lanYz\nTm0vNjMzajRyR3g5SFQ4N1JFUG5zaTVIRzlLR0NHcVlhN2FxNmFjVzRCYmVh\nakVsb2xiK1lmbEVIeEU1M0JnVGVLQi9mMjVId3VWTk03bnVzWndENThkWXpj\nYzhBWnRSb0VOWmxuRWMrQk03VHZwbUJCNzhUQWFXUzBiaVVJUDZkcis2RG1u\nWjlmaS9NOFpOSng3cExPNmJQSGljT0ZIbW96WE03VDlnVkhrcVNIRmQ5T2F5\ncmorZ3ZVUk9lNTBxMGZITWlWRXNjamtxR2tCVFhaL1RHS0MvYVNUR05hZjhu\nWlQ0akFiM1ZmcENaWE80RElGWGwzTUc4R3FqTmZtbkZuZHl6aklYNEVEcUEr\nTU15UnJCRGNuK1kzbjJDRVNtYi95eVZTY2NiUmpFeU1RaFBjWUUrT3duV2Mx\nYksyMXR0eHNIZXF0S1IxbXI4bWlxbHk3S3NtaG5XNldxOVFBc096WW5DNW1s\nU0RjV2FrSkNWdEhHZitBTUl4RGtxb2lkYVZoaFRKWld1TUxKSTFqRUNvZzFL\nYkMzOG1aMXFmMXg4eXBraS9DZ3FUdC9CdGV0MmNYcGxNa1p0ZVhHak5CVkI4\nUEtma0NUMnNiYUlPVS83NTZJY0NmZ2NTQVM5SnJyQTNZc3JUaW9pNHBWM2NN\nR0tiMTdabEovYXVLV2UxVHVEMmJqUUZIYVVveFc3UzNMcGxFYVVwRmo0anAr\neDkza1Rmem5Ba1pQQTBMTFVEUzBHMkd2L1Y4WnBpenI2dGRFL3YyZVk4cGVR\nbVVVZzZNb3NqRVJWbjhyblVFS0pNTUw1aXpscjI0OWtTSTQ2eWk0QzVOL21G\nN2hYT0crN1pSMEVpeXlZdERONnNTZ1QxaUxGTGdrRDN4Lzh0bUo0T3Nua2ZQ\nY1FPL0xrV09hWHJKOCtJWjMyQjRwblZSczd4RW4vb3FiMHp1SG5rR3FSakRJ\nS2pNUUd0RTQzMzg2bjhqM0VBVkVmS0NKa3hPSWZQeVV3dm53MTRjbTVBU2JS\nc3QwZnB3ODhzRkw0UUo0MGtoS1hGSFNzQW14TmltMVdDZFY1aGloQ1daUXJT\ndmVaMW1wVDIxRWZZdHlzZz09LjNXbUx4UDFkT0ZEdmlwblEuK0FMeHNwOXo0\nUlpaR25lZTM5d1kxZz09Iiwic2lnIjoiTW0zckRNZ09SMURYUVc4S3ZJYnI4\nbGpxTGFGTzFleldqOUd3TnArNmNuTExkMXJIc2N4N2xpZXJSOFViUjZYM0Vy\nUDQ0blpxamJkeUcwL3hrOHVEQUE9PSIsImFsZyI6ImFlcy0yNTYtZ2NtK2Vk\nMjU1MTkifQ==\n-----END LICENSE FILE-----\n";
    String licenseKey = "33362C-D254BA-F54C3C-DAAE48-C71975-V3";
    String publicKey = "e8601e48b69383ba520245fd07971e983d06d22c4257cfd82304601479cee788";

    // Parse signed license file (removing cert header, newlines and footer)
    String encodedPayload = licenseFile.replaceAll("(^-----BEGIN LICENSE FILE-----\\n|\\n|-----END LICENSE FILE-----\\n$)", "");
    byte[] payloadBytes = Base64.getDecoder().decode(encodedPayload);
    String payload = new String(payloadBytes);
    String encryptedData = "";
    String encodedSignature = "";
    String algorithm = "";

    try {
      JSONObject attrs = new JSONObject(payload);
      encryptedData = (String) attrs.get("enc");
      encodedSignature = (String) attrs.get("sig");
      algorithm = (String) attrs.get("alg");
    } catch (JSONException e) {
      System.out.println(
        String.format("Failed to parse license file: %s", e.getMessage())
      );

      return;
    }

    // Verify license file algorithm
    if (!algorithm.equals("aes-256-gcm+ed25519")) {
      System.out.println("Unsupported algorithm");

      return;
    }

    // Decode base64 signature and signing data to byte arrays
    byte[] signatureBytes = Base64.getDecoder().decode(encodedSignature);
    String signingData = String.format("license/%s", encryptedData);
    byte[] signingDataBytes = signingData.getBytes();

    // Convert hex-encoded public key to a byte array
    byte[] publicKeyBytes = Hex.decode(publicKey);

    // Set up Ed25519 verifier
    Ed25519PublicKeyParameters verifierParams = new Ed25519PublicKeyParameters(publicKeyBytes, 0);
    Ed25519Signer verifier = new Ed25519Signer();

    verifier.init(false, verifierParams);
    verifier.update(signingDataBytes, 0, signingDataBytes.length);

    // Verify the signature
    boolean ok = verifier.verifySignature(signatureBytes);
    if (ok) {
      System.out.println("License file signature is valid!");

      // The decrypted plaintext dataset
      String plaintext = "";

      // Decrypt the license file
      try {
        // Parse the encrypted data
        String encodedCiphertext = encryptedData.split("\\.", 3)[0];
        String encodedIv = encryptedData.split("\\.", 3)[1];
        String encodedTag = encryptedData.split("\\.", 3)[2];

        // Decode ciphertext, IV and tag to byte arrays
        byte[] ciphertext = Base64.getDecoder().decode(encodedCiphertext);
        byte[] iv = Base64.getDecoder().decode(encodedIv);
        byte[] tag = Base64.getDecoder().decode(encodedTag);
        byte[] key = new byte[256];

        // Hash license key with SHA-256 to obtain encryption key
        try {
          byte[] licenseKeyBytes = licenseKey.getBytes();
          MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

          sha256.update(licenseKeyBytes);

          key = sha256.digest();
        } catch (NoSuchAlgorithmException e) {
          System.out.println(
            String.format("Failed to hash license key: %s", e.getMessage())
          );

          return;
        }

        // Set up AES-256-GCM
        AEADParameters cipherParams = new AEADParameters(new KeyParameter(key), 128, iv, null);
        GCMBlockCipher cipher = new GCMBlockCipher(new AESEngine());

        cipher.init(false, cipherParams);

        // Concat ciphertext and authentication tag to produce cipher input
        byte[] input = new byte[ciphertext.length + tag.length];

        System.arraycopy(ciphertext, 0, input, 0, ciphertext.length);
        System.arraycopy(tag, 0, input, ciphertext.length, tag.length);

        // Decrypt the ciphertext
        byte[] output = new byte[cipher.getOutputSize(input.length)];

        int len = cipher.processBytes(input, 0, input.length, output, 0);

        // Validate authentication tag
        cipher.doFinal(output, len);

        plaintext = new String(output);
      } catch (IllegalArgumentException | IllegalStateException | DataLengthException | InvalidCipherTextException e) {
        System.out.println(
          String.format("Failed to decrypt license file: %s", e.getMessage())
        );

        return;
      }

      System.out.println("License file was successfully decrypted!");
      System.out.println(
        String.format("> Decrypted: %s", plaintext)
      );
    } else {
      System.out.println("License file signature is invalid!");
    }
  }
}

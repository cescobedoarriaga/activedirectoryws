/**
 * 
 */
package mx.com.engen.activedirectoryws.ldap;

/**
 * @author Carlos Escobedo Arriaga
 * @see <a href=
 * "https://docs.microsoft.com
 * /en-us/troubleshoot/windows-server/identity/useraccountcontrol-manipulate-account-properties">
 * https://docs.microsoft.com/en-us/troubleshoot/windows-server/identity/
 * useraccountcontrol-manipulate-account-properties
 * </a>
 *
 */
public final class UserAccountControlValues {
  /**
   * SCRIPT.
   */
  public static final int SCRIPT = 1;
  /**
   * ACCOUNTDISABLE.
   */
  public static final int ACCOUNTDISABLE = 514;
  /**
   * HOMEDIR_REQUIRED.
   */
  public static final int HOMEDIR_REQUIRED = 8;
  /**
   * LOCKOUT.
   */
  public static final int LOCKOUT = 16;
  /**
   * PASSWD_NOTREQD.
   */
  public static final int PASSWD_NOTREQD = 32;
  /**
   * PASSWD_CANT_CHANGE.
   */
  public static final int PASSWD_CANT_CHANGE = 64;
  /**
   * ENCRYPTED_TEXT_PWD_ALLOWED.
   */
  public static final int ENCRYPTED_TEXT_PWD_ALLOWED = 128;
  /**
   * TEMP_DUPLICATE_ACCOUNT.
   */
  public static final int TEMP_DUPLICATE_ACCOUNT = 256;
  /**
   * NORMAL_ACCOUNT.
   */
  public static final int NORMAL_ACCOUNT = 512;
  /**
   * INTERDOMAIN_TRUST_ACCOUNT.
   */
  public static final int INTERDOMAIN_TRUST_ACCOUNT = 2_048;
  /**
   * WORKSTATION_TRUST_ACCOUNT.
   */
  public static final int WORKSTATION_TRUST_ACCOUNT = 4_096;
  /**
   * SERVER_TRUST_ACCOUNT.
   */
  public static final int SERVER_TRUST_ACCOUNT = 8_192;
  /**
   * DONT_EXPIRE_PASSWORD.
   */
  public static final int DONT_EXPIRE_PASSWORD = 65_536;
  /**
   * MNS_LOGON_ACCOUNT.
   */
  public static final int MNS_LOGON_ACCOUNT = 131_072;
  /**
   * SMARTCARD_REQUIRED.
   */
  public static final int SMARTCARD_REQUIRED = 262_144;
  /**
   * TRUSTED_FOR_DELEGATION.
   */
  public static final int TRUSTED_FOR_DELEGATION = 524_288;
  /**
   * NOT_DELEGATED.
   */
  public static final int NOT_DELEGATED = 1_048_576;
  /**
   * USE_DES_KEY_ONLY.
   */
  public static final int USE_DES_KEY_ONLY = 2_097_152;
  /**
   * DONT_REQ_PREAUTH.
   */
  public static final int DONT_REQ_PREAUTH = 4_194_304;
  /**
   * PASSWORD_EXPIRED.
   */
  public static final int PASSWORD_EXPIRED = 8_388_608;
  /**
   * TRUSTED_TO_AUTH_FOR_DELEGATION.
   */
  public static final int TRUSTED_TO_AUTH_FOR_DELEGATION = 16_777_216;
  /**
   * PARTIAL_SECRETS_ACCOUNT.
   */
  public static final int PARTIAL_SECRETS_ACCOUNT = 67_108_864;
  
  private UserAccountControlValues() { }
}

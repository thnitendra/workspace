package nit.poc.xa;

/**
 * Before using JTA, you must first implement an Xid class for identifying transactions (this would normally be done by the transaction manager). The Xid contains three elements: formatID, gtrid (global transaction ID), and bqual (branch qualifier ID).
 *
 * The formatID is usually zero, meaning that you are using the OSI CCR (Open Systems Interconnection Commitment, Concurrency, and Recovery standard) for naming. If you are using another format, the formatID should be greater than zero A value of -1 means that the Xid is null.
 *
 * The gtrid and bqual can each contain up to 64 bytes of binary code to identify the global transaction and the branch transaction, respectively. The only requirement is that the gtrid and bqual taken together must be globally unique. Again, this can be achieved by using the naming rules specified in the OSI CCR.
 */
import javax.transaction.xa.Xid;

class MyXid implements Xid
{
 protected int formatId;
 protected byte gtrid[];
 protected byte bqual[];
 public MyXid()
 {
 }
 public MyXid(int formatId, byte gtrid[], byte bqual[])
 {
 this.formatId = formatId;
 this.gtrid = gtrid;
 this.bqual = bqual;
 }
 public int getFormatId()
 {
 return formatId;
 }
 public byte[] getBranchQualifier()
 {
 return bqual;
 }
 public byte[] getGlobalTransactionId()
 {
 return gtrid;
 }
}
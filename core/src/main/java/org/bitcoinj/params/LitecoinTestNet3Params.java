package org.bitcoinj.params;

import org.bitcoinj.core.BitcoinSerializer;
import org.bitcoinj.core.Utils;
import org.spongycastle.util.encoders.Hex;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the testnet, a separate public instance of Litecoin that has
 * relaxed rules suitable for development and testing of applications and new
 * Litecoin versions.
 */
public class LitecoinTestNet3Params extends AbstractBitcoinNetParams {
    public static final int TESTNET_MAJORITY_WINDOW = 1000;
    public static final int TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public LitecoinTestNet3Params() {
        super();
        id = ID_LITECOIN_TESTNET;
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);

        dumpedPrivateKeyHeader = 239;
        addressHeader = 111;
        p2shHeader = 196;
        acceptableAddressCodes = new int[]{addressHeader, p2shHeader};
        port = 19333;
        packetMagic = 0xf9beb4d9L;
        bip32HeaderPub = 0x0488B21E; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"

        subsidyDecreaseBlockCount = 210000;
        spendableCoinbaseDepth = 500;

        genesisBlock.setTime(1320884152L);
        genesisBlock.setDifficultyTarget(0x1d018ea7L);
        genesisBlock.setNonce(3562614017L);

        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f"),
                genesisHash);

        majorityEnforceBlockUpgrade = TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = TESTNET_MAJORITY_WINDOW;
    }

    private static LitecoinTestNet3Params instance;

    public static synchronized LitecoinTestNet3Params get() {
        if (instance == null) {
            instance = new LitecoinTestNet3Params();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return ID_LITECOIN_TESTNET;
    }

    @Override
    public BitcoinSerializer getSerializer(boolean parseRetain) {
        return new BitcoinSerializer(this, parseRetain);
    }
}

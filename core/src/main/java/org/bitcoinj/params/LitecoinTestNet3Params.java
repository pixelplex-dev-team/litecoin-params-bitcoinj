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
public class LitecoinTestNet3Params extends AbstractLitecoinParams {
    public static final int TESTNET_MAJORITY_WINDOW = 100;
    public static final int TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED = 75;
    public static final int TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 51;

    public LitecoinTestNet3Params() {
        super();
        id = ID_LITECOIN_TESTNET;
        // Genesis hash is ed60044f49d5e62ac5403ca26e24ae5666f03eb034b4f41cf20e976266bf99b4
        packetMagic = 0xfcc1b7dc;

        maxTarget = Utils.decodeCompactBits(0x1e0fffffL);
        port = 19333;
        addressHeader = 111;
        p2shHeader = 196;
        acceptableAddressCodes = new int[]{addressHeader, p2shHeader};
        dumpedPrivateKeyHeader = 239;

        spendableCoinbaseDepth = 30;
        subsidyDecreaseBlockCount = 100000;

        genesisBlock.setTime(1317798646L);
        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setNonce(385270584);

        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("ed60044f49d5e62ac5403ca26e24ae5666f03eb034b4f41cf20e976266bf99b4"));
        alertSigningKey = Hex.decode("0449623fc74489a947c4b15d579115591add020e53b3490bf47297dfa3762250625f8ecc2fb4fc59f69bdce8f7080f3167808276ed2c79d297054367566038aa82");

        majorityEnforceBlockUpgrade = TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = TESTNET_MAJORITY_WINDOW;

        dnsSeeds = new String[]{
                "testnet-seed.litecointools.com",
                "testnet-seed.ltc.xurious.com",
                "dnsseed.wemine-testnet.com"
        };

        bip32HeaderPub = 0x043587CF;
        bip32HeaderPriv = 0x04358394;
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

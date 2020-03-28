package io.github.pinkteammodfest.railbot.robot.power;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

// represents a channel of power transmission.
public interface PowerChannel<P> extends Comparable<PowerChannel<? super P>> {

  Identifier getId();

  P empty();

  // contract: use (empty(), x) -> result(empty(), x)
  BinaryOpResult<P> use(P original, P used);

  // contract: cumulate (empty(), x) -> result((not empty), some leftover/empty) ()
  // may have right result if the channel has a natural cap
  BinaryOpResult<P> cumulate(P original, P supplied);

  @Override
  default int compareTo(@NotNull PowerChannel<? super P> o) {
    return getId().compareTo(o.getId());
  }

  static <P> BinaryOpResult<P> usedHasMore(PowerChannel<P> channel, P remainder) {
    return new BinaryOpResult<>(remainder, channel.empty());
  }

  static <P> BinaryOpResult<P> usedNeedsMore(PowerChannel<P> channel, P remainder) {
    return new BinaryOpResult<>(channel.empty(), remainder);
  }

  final class BinaryOpResult<P> {

    private final P left;
    private final P right;

    public BinaryOpResult(P left, P right) {
      this.left = left;
      this.right = right;
    }

    public P getLeft() {
      return left;
    }

    public P getRight() {
      return right;
    }
  }

  final class Entry<P> {

    private final PowerChannel<P> channel;
    private final P value;

    public Entry(PowerChannel<P> channel, P value) {
      this.channel = channel;
      this.value = value;
    }

    public PowerChannel<P> getChannel() {
      return channel;
    }

    public P getValue() {
      return value;
    }
  }
}

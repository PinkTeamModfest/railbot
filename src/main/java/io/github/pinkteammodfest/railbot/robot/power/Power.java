package io.github.pinkteammodfest.railbot.robot.power;

import io.github.pinkteammodfest.railbot.robot.power.PowerChannel.BinaryOpResult;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

// analogous to a Map<PowerChannel, P>
public final class Power {

  private final Map<PowerChannel<?>, Object> channels;

  public Power(Map<PowerChannel<?>, Object> map) {
    this.channels = map;
  }

  @SuppressWarnings("unchecked")
  public <P> BinaryOpResult<P> add(PowerChannel<P> channel, P value) {
    P[] rightArr = (P[]) new Object[]{null};
    P left = (P) channels.compute(channel, (k, oldV) -> {
      if (oldV == null) {
        rightArr[0] = null;
        return value;
      }
      BinaryOpResult<P> afterCumulate = ((PowerChannel<P>) k).cumulate((P) oldV, value);
      rightArr[0] = afterCumulate.getRight();
      return afterCumulate.getLeft();
    });
    P right = rightArr[0];
    return new BinaryOpResult<>(left == null ? channel.empty() : left, right == null ? channel.empty() : right);
  }

  @SuppressWarnings("unchecked")
  public <P> BinaryOpResult<P> use(PowerChannel<P> channel, P value) {
    P[] rightArr = (P[]) new Object[]{null};
    P left = (P) channels.compute(channel, (k, oldV) -> {
      if (oldV == null) {
        rightArr[0] = value;
        return null;
      }
      BinaryOpResult<P> afterUse = ((PowerChannel<P>) k).use((P) oldV, value);
      rightArr[0] = afterUse.getRight();
      return afterUse.getLeft();
    });
    P right = rightArr[0];
    return new BinaryOpResult<>(left == null ? channel.empty() : left, right == null ? channel.empty() : right);
  }


  @SuppressWarnings("unchecked")
  public <P> @Nullable P get(PowerChannel<P> channel) {
    return (P) channels.getOrDefault(channel, channel.empty());
  }
}

package liu.york.client.one.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.agent.model.NewCheck;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.Timer;
import java.util.TimerTask;

public class CheckTtl {

    private ConsulClient consulClient;

    private String checkId;
    private NewCheck check;
    private Timer timer;

    private int ttlDelay = 5000;
    private int ttlPeriod = 10000;

    public CheckTtl(String checkId, ConsulClient consulClient) {
        this.checkId = checkId;
        this.consulClient = consulClient;
    }

    public void agentCheckRegister() {
        this.check = new NewCheck();
        check.setId(checkId);
        check.setName(checkId);
        check.setTtl("30s");
        check.setInterval("10s");
        check.setTimeout("10s");
        this.consulClient.agentCheckRegister(check);
    }

    public void agentCheckDegister() {
        if (this.checkId != null) {
            this.consulClient.agentCheckDeregister(checkId);
        }
    }


    public boolean isRunning() {
        if (this.timer == null) {
            return false;
        }
        return true;
    }

    public void start() {
        if (!isRunning()) {
            agentCheckRegister();
            consulClient.agentCheckPass(checkId);
            this.timer = new Timer();
            timer.scheduleAtFixedRate(new TtlTask(), ttlDelay, ttlPeriod);
        }
    }

    public void stop() {
        if (this.timer != null) {
            agentCheckDegister();
            timer.cancel();
        }
    }

    class TtlTask extends TimerTask {

        @Override
        public void run() {
            try {
                consulClient.agentCheckPass(checkId);
            } catch (OperationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ConsulClient getConsulClient() {
        return consulClient;
    }

    public void setConsulClient(ConsulClient consulClient) {
        this.consulClient = consulClient;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public NewCheck getCheck() {
        return check;
    }

    public void setCheck(NewCheck check) {
        this.check = check;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getTtlDelay() {
        return ttlDelay;
    }

    public void setTtlDelay(int ttlDelay) {
        this.ttlDelay = ttlDelay;
    }

    public int getTtlPeriod() {
        return ttlPeriod;
    }

    public void setTtlPeriod(int ttlPeriod) {
        this.ttlPeriod = ttlPeriod;
    }
}
package co.ooci.event.internal;

import co.ooci.event.entity.OociServerInfo;
import co.ooci.event.manager.OociEvent;
import lombok.Getter;

@Getter
public  class ServerOnlineEvent extends OociEvent {

    protected OociServerInfo serverInfo;

    public ServerOnlineEvent(OociServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }
}

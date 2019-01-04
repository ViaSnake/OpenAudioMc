class MediaManager {

    constructor() {
        this.sounds = {};
    }

    destroySounds(key) {
        openAudioMc.debugPrint("<b>starting to quit fade </b> " + key)
        let that = this;
        that.sounds[key].setVolume(0, 300, function () {
            openAudioMc.debugPrint("<b>finished fading</b> " + key + "")
            if (that.sounds[key] != null) that.sounds[key].destroy();
            delete that.sounds[key];
            openAudioMc.debugPrint("<b>stopping</b> " + key + " <b>after fading</b>")
        });
    }

    getSound(id) {
        return this.sounds[id];
    }

    registerMedia(id, media) {
        this.sounds[id] = media;
        openAudioMc.debugPrint("<b>created media</b> " + id + "")
    }

}
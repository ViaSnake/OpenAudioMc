import React from "react";
import {connect} from "react-redux";
import {setGlobalState} from "../../state/store";

class ResetLanguageBanner extends React.Component {

    nulifyBanner() {
        setGlobalState({translationBanner: null});
    }

    render() {
        if (this.props.translationBanner === null) {
            return null;
        }

        return (
            <div className="bg-gray-100 flex flex-col justify-center" style="display:none;" id="lang-change-banner">
                <div
                    className="max-w-screen-lg mx-auto fixed bg-white inset-x-5 p-5 bottom-5 rounded-lg drop-shadow-2xl flex gap-4 flex-wrap md:flex-nowrap text-center md:text-left items-center justify-center md:justify-between">
                    <div className="w-full text-black">{this.props.translationBanner.detectedAs}</div>
                    <div className="flex gap-4 items-center flex-shrink-0">
                        <button className="text-indigo-600 focus:outline-none hover:underline" onClick={this.props.translationBanner.reset}>
                            {this.props.translationBanner.toEn}
                        </button>
                        <button className="bg-indigo-500 px-5 py-2 text-white rounded-md hover:bg-indigo-700 focus:outline-none" onClick={this.nulifyBanner}>
                            {this.props.translationBanner.keep}
                        </button>
                    </div>
                </div>
            </div>
        );
    }
}

export default connect(mapStateToProps)(ResetLanguageBanner);
function mapStateToProps(state) {
    return {
        translationBanner: state.translationBanner
    };
}
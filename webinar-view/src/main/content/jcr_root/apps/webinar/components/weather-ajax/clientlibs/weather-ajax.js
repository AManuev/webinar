/**
 * Weather Ajax component
 *
 * @author Andrii_Manuiev@epam.com
 * @version 2.0
 * @date-created 2014.02.27
 * @desc Weather Ajax component
 * @global jQuery
 */

(function ($) {

    $(function () {

        var $weatherAjax = $('#global-weather-ajax');

        if (!$weatherAjax.size()) return;

        var $weatherAjaxBox = $weatherAjax.find('.global-weather-ajax__info');

//      get URL from data attribute
        var servletUrl = $weatherAjax.data('search-url');

        console.info('"Weather-Ajax" >> initialized');


        function getWeatherData() {

//          Use an ExtJs framework when you extend any admin UI.

//            CQ.Ext.Ajax.request({
//                method: 'GET',
//                disableCaching: true,
//                url: servletUrl,
//                success: function() {
//                    $weatherAjaxBox.text(data);                },
//                failure: function() {
//                    CQ.Ext.Msg.alert('Info', 'Failed to set new tags for products');
//                }
//            });

// in general you can work with JQuery

            $.ajax({
                url: servletUrl,
                type: 'GET',
                dataType: 'text',
                cache: false,
                success: function (data) {
                    console.info(data);

                    $weatherAjaxBox.text(data);
                },
                error: function (jqXhr, ajaxOptions, thrownError) {
                    console.error('"Weather-Ajax" >> error: request failed!',
                        jqXhr, ajaxOptions, thrownError);
                }
            });
        }

        getWeatherData();
    });
})(jQuery);

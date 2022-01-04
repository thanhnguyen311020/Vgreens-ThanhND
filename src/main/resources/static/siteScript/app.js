paypal.Buttons.driver("angular", window.angular);
var app = angular.module('vgreens-site-app', ['paypal-buttons','angularUtils.directives.dirPagination']);
Toast.setTheme(TOAST_THEME.DARK);
Toast.setPlacement(TOAST_PLACEMENT.TOP_RIGHT);
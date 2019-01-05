document.addEventListener("turbolinks:load", function() {
    console.debug("Initial layout start");
    $('.profile-dropdown-button').dropdown({
        inDuration: 300,
        outDuration: 225,
        constrainWidth: false,
        hover: true,
        gutter: 0,
        belowOrigin: true,
        alignment: 'right',
        stopPropagation: false
    });

    $('.collapsible.collapsible-accordion > li').each((index, dom) => {
        if($(dom).find(".collapsible-body li.active").length != 0){
            $(dom).addClass("active");
        }
    });
    
    $('.collapsible').collapsible();
    $('#nav-mobile').sidenav({
        edge: 'left'
    });
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        maxDate: new Date()
    });

    require('./component/_delete');
    $('.modal').modal();
})
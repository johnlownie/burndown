<style type="text/css">
    #by-category svg path:hover,
    #by-month svg rect:hover {
        cursor: pointer;
    }
    
    .btn-group {
        margin-top: 10px;
    }
    
    .btn-group > .btn {
        position: relative;
        flex: 1 1 auto;
    }

    .btn-check {
        position: absolute;
        clip: rect(0,0,0,0);
        pointer-events: none;
    }
    
    .btn-check:checked + .btn, .btn.active, .btn.show, .btn:first-child:active, :not(.btn-check) + .btn:active {
        color: #fff;
        background-color: #579ddb;
        border-color: #5fa2dd;
    }
    
    @media (min-width: 768px) {
        .searchbx {
            display: table-cell;
            padding-right: 20px;
            vertical-align: middle;
        }
    }
    
    @media (max-width: 512px) {
        .btn-group {
            display: none;
        }
    }
</style>
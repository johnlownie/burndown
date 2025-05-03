<style type="text/css">
    .radio {
        cursor: pointer !important;
        user-select: none !important;
        -webkit-user-select: none !important;
        -webkit-touch-callout: none !important;
    }

    .radio img {
        box-shadow: 0 0 0 4px rgba(0, 0, 0, 0.1);
    }

    .radio > input {
        visibility: hidden !important;
        position: absolute !important;
    }

    .radio > input:checked + img {
        box-shadow: 0 0 0 4px #9bc5ea;
    }

    #avatar-list {
        display: inline-table;
        width: 100%;
    }

    #avatar-list li {
        display: table-cell;
        text-align: center;
    }
</style>

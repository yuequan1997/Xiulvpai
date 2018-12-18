const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const FixStyleOnlyEntriesPlugin = require("webpack-fix-style-only-entries");

module.exports = {
    mode: 'development',
    devtool: 'cheap-module-source-map',
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, 'src/main/resources/static/assets'),
    },
    plugins: [
        new FixStyleOnlyEntriesPlugin(),
        new MiniCssExtractPlugin({filename: '[name].css'}),
    ],
};
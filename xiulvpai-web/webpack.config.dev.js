const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
    mode: 'development',
    devtool: 'cheap-module-source-map',
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, 'src/main/resources/static/assets'),
    },
    plugins: [
        new MiniCssExtractPlugin({filename: '[name].css'}),
    ],
};